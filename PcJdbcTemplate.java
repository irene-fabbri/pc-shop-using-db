package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

@Component
public class PcJdbcTemplate {
	// richimamo un oggetto JdbcTemplate
	private JdbcTemplate jdbcTemplateObject;

	/*
	 * Andiamo a iniettare questo oggetto nella classe dipJdbcTemplate tramite il
	 * metodo set
	 */
	@Autowired
	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}

	public int insertPc(String nome, String marca, String descrizione, double prezzo, String url, int qntMagazzino) {

		String query = "INSERT INTO pc_shop (nome, marca, descrizione, prezzo, url, qntMagazzino) VALUES (?, ?, ?, ?, ?, ?)";
		return jdbcTemplateObject.update(query, nome, marca, descrizione, prezzo, url, qntMagazzino);
	}

	public int delete(String nome, String marca) {
		String query = "DELETE FROM pc_shop WHERE nome = ? AND marca = ?";
		return jdbcTemplateObject.update(query, nome, marca);
	}

	public ArrayList<Pc> getLista() {
		// seleziona tutti i record da eventi
		String query = "SELECT * FROM pc_shop";

		// il metodo esegue la query e come secondo parametro crea un result set
		// extractor
		return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<Pc>>() {
			// l'oggetto resultSetExtractor ha il metodo extractData che deve essere
			// obbligatoriamente implementato
			@Override
			public ArrayList<Pc> extractData(ResultSet rs) throws SQLException, DataAccessException {

				// creiamo un arraylist di prodotto che ci servirà come valore di ritorno del
				// metodo
				ArrayList<Pc> listaPc = new ArrayList<>();

				// andiamo a iterare il resulta set
				while (rs.next()) {

					Pc pc = new Pc();
					// con i risultati del result set abbiamo instanziato un oggetto prodotto e lo
					// abbiamo
					// aggiunto alla lista
					pc.setNome(rs.getString("nome"));
					pc.setMarca(rs.getString("marca"));
					pc.setDescrizione(rs.getString("descrizione"));
					pc.setPrezzo(rs.getDouble("prezzo"));
					pc.setUrl(rs.getString("url"));
					pc.setQntMagazzino(rs.getInt("qntMagazzino"));
					pc.setQntVenduta(rs.getInt("qntVenduta"));

					listaPc.add(pc);

				}

				return listaPc;
			}

		});

	}

	public int updateQnt(int num, String nome) {
		String query = "UPDATE pc_shop SET (qntMagazzino, qntVenduta) = (qntMagazzino - ?, qntVenduta + ?) WHERE nome = ?";
		return jdbcTemplateObject.update(query, num, num, nome);
	}

}
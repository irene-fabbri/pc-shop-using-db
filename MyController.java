package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;

@Controller
public class MyController {

	@Autowired
	EmailService emS;
	/*
	 * Creiamo un oggetto di tipo dipJdbcTemplate
	 */
	PcJdbcTemplate db;
	ArrayList<Pc> listapc = new ArrayList<>();
	ArrayList<PcSelezionato> pcSelezionati = new ArrayList<>();

	/*
	 * Questo oggetto lo iniettiamo nel controller tramite costruttore
	 */
	@Autowired
	public MyController(PcJdbcTemplate db) {
		this.db = db;
		this.listapc = db.getLista();
	}

	@GetMapping("/")
	public String getIndex(Model m1) {
		m1.addAttribute("listapc", listapc);
		return "index";
	}

	@PostMapping("/process")
	public String buy(@RequestParam("nome") String nome, @RequestParam("num") int num) {

		if (num > 0) {
			// trovare l'oggetto con quel nome
			for (Pc pc : listapc) {
				if (pc.getNome().equals(nome)) {
					// se disponibile in magazzino
					if (pc.qntMagazzino >= num) {
						// aggiungerlo al carrello
						pcSelezionati.add(new PcSelezionato(pc, num));
						// diminuire la quantità disponibile e aumentare la quantità venduta
						// TO DO: aggiornare db
						// db.updateQnt(num, nome);
						// pc.qntMagazzino -= num;
						// pc.qntVenduta += num;
					}
				}
			}
		}
		return "redirect:/carrello";
	}
	
	@PostMapping("/rimuovi")
	public String removeProduct(@RequestParam("nome") String nome) {
		pcSelezionati.removeIf(pc -> pc.getNome().equals(nome));
		return "redirect:/carrello";
	}

	@PostMapping("/modifica")
	public String rimuovi(@RequestParam("nome") String nome, @RequestParam("num") int num) {
		for(Pc pc : listapc) {
			if(pc.getNome().equals(nome)) {
				for(PcSelezionato pcCom : pcSelezionati) {
					if (pcCom.getNome().equals(nome) && pc.qntMagazzino >= num) {
						pcCom.setQnt(num);
						break;
					}
				}				
			}
		}
		return "redirect:/carrello";
	}

	@GetMapping("/carrello")
	public String printCarrello(Model m1) {
		double somma = 0;
		for (PcSelezionato pc : pcSelezionati) {
			somma += pc.getPrezzo() * pc.getQnt();
		}
		m1.addAttribute("somma", somma);
		m1.addAttribute("lista", pcSelezionati);
		return ("carrello");
	}
	
	@PostMapping("/compra")
	public String fattura(Model m1){
		double somma = 0;
		
		int rowsAffected = 0;
		for (PcSelezionato pc : pcSelezionati) {
			somma += pc.getPrezzo() * pc.getQnt();
			rowsAffected += db.updateQnt(pc.getQnt(), pc.getNome());
		}
		System.err.println("Numero di righe aggiornate :"+rowsAffected);
		m1.addAttribute("somma", somma);
		m1.addAttribute("lista", pcSelezionati);
		return ("fattura");	
	}
	
	@GetMapping("/email")
	public String fatturaEmail(@RequestParam("mail") String mail,Model m1) throws MessagingException{
		double somma = 0;
		
		StringBuilder message = new StringBuilder("<h1>Riepilogo acquisti:</h1>"
				+ "<table><thead><tr><th>Nome</th><th>Marca</th><th>Prezzo</th><th>Quantità</th><th>Immagine</th></tr><tbody>");
		for (PcSelezionato pc : pcSelezionati) {
			somma += pc.getPrezzo() * pc.getQnt();
			message.append("<tr><td>").append(pc.getNome()).append("</td><td>").append(pc.getMarca()).append("</td><td>").append(pc.getPrezzo()).append(" €</td>"
					+ "<td>").append(pc.getQnt()).append("</td><td><img src='").append(pc.getUrl()).append("'style='width: 5vw; height: auto; margin: 10px;'/></td></tr>");
		}
		message.append("</tbody></table><h3>Totale: ").append(somma).append(" € </h3><footer>Grazie per aver acquistato da La Gang dei PC</footer>");
		emS.sendEmail(mail ,"Grazie di aver acquistato da noi",message.toString());
		System.out.println("Acquisto avvenuto con successo");
		return "redirect:/";
	}
}

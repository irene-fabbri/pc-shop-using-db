package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

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
	
	/*
	 * @PostMapping("/process") public String getCarrello(@RequestParam("nome")
	 * ArrayList<String> nomi,
	 * 
	 * @RequestParam("num") ArrayList<Integer> numeri,Model m1) {
	 * 
	 * //listapcselezionati.removeAll(listapcselezionati); for (int i = 0; i <
	 * nomi.size(); i++) {
	 * 
	 * String str = "Hai selezionato: "; String str1 = "Al costo di: ";
	 * 
	 * if (numeri.get(i) != 0) {
	 * 
	 * str += nomi.get(i) + " " + numeri.get(i) + " volte\n"; somma += numeri.get(i)
	 * * listapc.get(i).getPrezzo(); str1 += somma + "€"; for(int j = 0;
	 * j<listapc.size(); j++) { if
	 * (listapc.get(j).nome.equalsIgnoreCase(nomi.get(i))) { // if
	 * (listapcselezionati.get(j).nome.equalsIgnoreCase(nomi.get(i))) { //
	 * listapcselezionati.get(j).qnt +=numeri.get(i); // }
	 * listapcselezionati.add(new Carrello(listapc.get(j).nome,
	 * listapc.get(j).marca, listapc.get(j).descrizione, listapc.get(j).prezzo,
	 * listapc.get(j).url,numeri.get(i))); } }
	 * 
	 * }
	 * 
	 * } System.out.println("La somma degli prezzi è: " + somma + " euro");
	 * 
	 * m1.addAttribute("somma", somma); m1.addAttribute("lista",
	 * listapcselezionati); return ("carrello");
	 * 
	 * }
	 * 
	 * @GetMapping("/carrello") public String printCarrello(Model m1){
	 * m1.addAttribute("somma", somma); m1.addAttribute("lista",
	 * listapcselezionati); return ("carrello"); }
	 * 
	 * @PostMapping("/rimuovi") public String rimuovi(@RequestParam("nome") String
	 * nome, @RequestParam("num") int numeri) { System.err.println("PRIMA"); for(Pc
	 * pc:listapcselezionati) { System.err.println(pc.toString()); } for(int j = 0;
	 * j<listapc.size(); j++) { if
	 * (listapcselezionati.get(j).nome.equalsIgnoreCase(nome)) { somma -=
	 * listapcselezionati.get(j).prezzo *numeri; listapcselezionati.get(j).qnt -=
	 * numeri; } } System.err.println("DOPO"); for(Pc pc:listapcselezionati) {
	 * System.err.println(pc.toString()); }
	 * 
	 * return "carrello"; }
	 */

	// *********************************************************************
	// PROF
	/*
	 * Il metodo submit riceve i dati dal form
	 * 
	 * @PostMapping("/submit") public String getDip(@RequestParam("nome") String
	 * nome,
	 * 
	 * @RequestParam("mansione") String mansione,
	 * 
	 * @RequestParam("stipendio") String stipendio) { // chiamiamo il metodo
	 * insertDip su d1 e li passiamo i dati ottenuti dal form int stipendioD =
	 * Integer.parseInt(stipendio); d1.insertDip(nome, mansione, stipendioD);
	 * 
	 * 
	 * return "form"; }
	 */
}
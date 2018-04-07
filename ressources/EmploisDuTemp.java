package ressources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmploisDuTemp {

	private Evenement event;

	private ArrayList<String> nomContact;
	private RepertoireContact repContact;
	private ArrayList<String> EmploisDusTemps;
	private BufferedWriter emploisDuTempsEmployer;
	private ArrayList<String> EmployerParticipantsEvenement;
	private ArrayList<String> nomEmployer;
	private RepertoireEvenement repEvent;
 
	public EmploisDuTemp(Evenement event) { 
		nomEmployer = new ArrayList<String>(); 
		EmploisDusTemps = new ArrayList<String>();
		this.event = event;

		nomContact = new ArrayList<String>();
		repContact = new RepertoireContact();// ajouter les employers qui vont participer � l'evenemnt
		EmployerParticipantsEvenement = new ArrayList<String>();
		repEvent = new RepertoireEvenement();
	}

	/**
	 * trois methodes importantes : ajouter evenement � l'emplois du temps de
	 * l'employer ajouter evenement � l'emplois du temps de l'entreprise enregistrer
	 * l'evenement
	 * 
	 * @param employer
	 *            0
	 */

	public void ajouterEmployerEvenemnt(Contact employer) {
		EmployerParticipantsEvenement.add(employer.getName());
	}

	public ArrayList<String> getNomContact() {
		return nomContact;
	}

	public void setNomContact(ArrayList<String> nomContact) {
		this.nomContact = nomContact;
	}

	public void lireEmploisDuTempEmployer(String nom) { 

		BufferedReader lireEmploisDutemps;
		String line = "";

		try {
			lireEmploisDutemps = new BufferedReader(new FileReader(".\\data\\" + nom + ".txt"));
			EmploisDusTemps.clear();
			try {
				while ((line = lireEmploisDutemps.readLine()) != null) {
					EmploisDusTemps.add(line);
				}
				lireEmploisDutemps.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void nomDesEmployerNecessaire(Evenement event) {
		nomEmployer.clear();
		for (int k = 0; k < event.getRepContact().getListeDesEmployer().size(); k += 3) {
			nomEmployer.add(event.getRepContact().getListeDesEmployer().get(k));
			System.out.println(event.getRepContact().getListeDesEmployer().get(k) + "...nomEMployernecessaire");

		}
	}

	public void nomContacts() {

	}

	public int ajouterDansEmploisDuTempsEmployer(Evenement event) throws IOException {
		String line = "";

		int dure = event.getReu().getDure();
		int heure = event.getReu().getHeure();
		// nomEmployer.clear();
		// nomDesEmployerNecessaire(event);
		
		File EmploisDuTempsEmploye = null;
		//if (event.getRepContact().getNomEmployer().size() != 0) {
			for (int j = 0; j < event.getRepContact().getNomEmployer().size(); j++) {
				System.out.println(event.getRepContact().getNomEmployer().get(j) + "derniere etape");
				EmploisDuTempsEmploye = new File(".\\data\\" + event.getRepContact().getNomEmployer().get(j) + ".txt");
				// EmploisDuTempsEmployer.delete();
				EmploisDusTemps.clear();
				System.out.println(event.getRepContact().getNomEmployer().size());
				System.out.println(event.getRepContact().getNomEmployer().get(j) + "....nom du contact1");
				BufferedReader lireEmploisDuTempEmployer = new BufferedReader(
						new FileReader(".\\data\\" + event.getRepContact().getNomEmployer().get(j) + ".txt"));
				while ((line = lireEmploisDuTempEmployer.readLine()) != null) {
					EmploisDusTemps.add(line);
				}
				lireEmploisDuTempEmployer.close();
				if ((EmploisDusTemps.get(heure).contains("1")) && (dure == 1)) {

					EmploisDusTemps.set(heure, "0");
					sauvegardeEmploisDuTempsEmployer(EmploisDusTemps, event.getRepContact().getNomEmployer().get(j));
					return 1;

				} else if ((EmploisDusTemps.get(heure).contains("1")) && (EmploisDusTemps.get(heure + 1).contains("1"))
						&& (dure == 2)) {

					EmploisDusTemps.set(heure, "0");
					EmploisDusTemps.set(heure + 1, "0");
					sauvegardeEmploisDuTempsEmployer(EmploisDusTemps, event.getRepContact().getNomEmployer().get(j));

					return 1;

				} else if ((EmploisDusTemps.get(heure).contains("1")) && (EmploisDusTemps.get(heure + 1) != "0")
						&& (EmploisDusTemps.get(heure + 2) != "0") && (dure == 3)) {

					EmploisDusTemps.set(heure, "0");
					EmploisDusTemps.set(heure + 1, "0");
					EmploisDusTemps.set(heure + 2, "0");
					sauvegardeEmploisDuTempsEmployer(EmploisDusTemps, event.getRepContact().getNomEmployer().get(j));

					return 1;

				} else if ((EmploisDusTemps.get(heure).contains("1")) && (EmploisDusTemps.get(heure + 1) .contains("1"))
						&& (EmploisDusTemps.get(heure + 2) .contains("1")) && (EmploisDusTemps.get(heure + 3).contains("1"))
						&& (dure == 4)) {

					EmploisDusTemps.set(heure, "0");
					EmploisDusTemps.set(heure + 1, "0");
					EmploisDusTemps.set(heure + 2, "0");
					EmploisDusTemps.set(heure + 3, "0");
					sauvegardeEmploisDuTempsEmployer(EmploisDusTemps, event.getRepContact().getNomEmployer().get(j));
					return 1;

				} else if ((EmploisDusTemps.get(heure) .contains("1")) && (EmploisDusTemps.get(heure + 1) .contains("1"))
						&& (EmploisDusTemps.get(heure + 2) != "0") && (EmploisDusTemps.get(heure + 3) != "0")
						&& (EmploisDusTemps.get(heure + 4) != "0") && (dure == 5)) {

					EmploisDusTemps.set(heure, "0");
					EmploisDusTemps.set(heure + 1, "0");
					EmploisDusTemps.set(heure + 2, "0");
					EmploisDusTemps.set(heure + 3, "0"); 
					EmploisDusTemps.set(heure + 4, "0");
					sauvegardeEmploisDuTempsEmployer(EmploisDusTemps, event.getRepContact().getNomEmployer().get(j));
					return 1;
				} else
					return 0;

			}
			//return 1;
	//	} else
			return 0;

	}

	public int ajouterDansEmploisDuTempsEntreprise(Evenement event) throws IOException {
		int heure = event.getReu().getHeure();
		int dure = event.getReu().getDure();
		File EmploisDuTempsEntreprise = new File(".\\data\\semaine1.txt");
		BufferedReader r = new BufferedReader(new FileReader(EmploisDuTempsEntreprise));
		String line = "";

		nomDesEmployerNecessaire(event);
		EmploisDusTemps.clear();
		while ((line = r.readLine()) != null) {

			EmploisDusTemps.add(line);
		}
		r.close();

		if (EmploisDusTemps.get(heure) != "0") {
			if (dure == 1) {
				EmploisDusTemps.set(heure, "0");
				repEvent.enregistrerEvenement(event);
				sauvegardeEmploisDuTemps(EmploisDusTemps);
				return 1;

			} else if (dure == 2) {
				if (EmploisDusTemps.get(heure + 1) .contains("1")) {
					EmploisDusTemps.set(heure, "0");
					EmploisDusTemps.set(heure + 1, "0");
					repEvent.enregistrerEvenement(event);
					sauvegardeEmploisDuTemps(EmploisDusTemps);
					return 1;

				} else {
					return 0;
				}
			} else if (dure == 3) {
				if ((EmploisDusTemps.get(heure + 2).contains("1")) && (EmploisDusTemps.get(heure + 1) .contains("1"))) {
					EmploisDusTemps.set(heure, "0");
					EmploisDusTemps.set(heure + 1, "0");
					EmploisDusTemps.set(heure + 2, "0");
					repEvent.enregistrerEvenement(event);
					sauvegardeEmploisDuTemps(EmploisDusTemps);
					return 1;

				} else {
					return 0;
				}
			} else if (dure == 4) {
				if ((EmploisDusTemps.get(heure + 3) .contains("1")) && (EmploisDusTemps.get(heure + 2) .contains("1"))
						&& (EmploisDusTemps.get(heure + 1) != "0")) {
					EmploisDusTemps.set(heure, "0");
					EmploisDusTemps.set(heure + 1, "0");
					EmploisDusTemps.set(heure + 2, "0");
					EmploisDusTemps.set(heure + 3, "0");
					repEvent.enregistrerEvenement(event);
					sauvegardeEmploisDuTemps(EmploisDusTemps);
					return 1;

				} else {

					return 0;
				}
			} else if (dure == 5) {
				if ((EmploisDusTemps.get(heure + 4) .contains("1")) && (EmploisDusTemps.get(heure + 3) .contains("1"))
						&& (EmploisDusTemps.get(heure + 2).contains("1")) && (EmploisDusTemps.get(heure + 1).contains("1"))) {

					EmploisDusTemps.set(heure, "0");
					EmploisDusTemps.set(heure + 1, "0");
					EmploisDusTemps.set(heure + 2, "0");
					EmploisDusTemps.set(heure + 3, "0");
					EmploisDusTemps.set(heure + 4, "0");
					repEvent.enregistrerEvenement(event);
					sauvegardeEmploisDuTemps(EmploisDusTemps);
					return 1;

				} else
					return 0;

			}

		} else
			return 0;

		return 0;

	}

	public void sauvegardeEmploisDuTempsEmployer(ArrayList tmp, String nom) throws IOException {
		File tmpFile = new File("data\\" + nom + ".txt");
		tmpFile.delete(); 
		BufferedWriter writeEmploisDuTempsEntreprise = new BufferedWriter(
				new FileWriter(new File("data\\" + nom + ".txt"), true));

		for (int i = 0; i < tmp.size(); i++) {
			writeEmploisDuTempsEntreprise.write(tmp.get(i) + "\r\n");
			System.out.println(tmp.get(i));
		}
		writeEmploisDuTempsEntreprise.close();
	}

	public void sauvegardeEmploisDuTemps(ArrayList tmp) throws IOException {
		File tmpFile = new File("data\\semaine1.txt");
		tmpFile.delete();
		BufferedWriter writeEmploisDuTempsEntreprise = new BufferedWriter(
				new FileWriter(new File("data\\semaine1.txt"), true));

		for (int i = 0; i < tmp.size(); i++) {
			writeEmploisDuTempsEntreprise.write(tmp.get(i) + "\r\n");
			System.out.println(tmp.get(i));
		}
		writeEmploisDuTempsEntreprise.close();

	}

	public void initialiserSemaine() {
		BufferedWriter semaine1;
		File semaine = new File(".\\data\\semaine1.txt");
		semaine.delete();
		try {
			semaine1 = new BufferedWriter(new FileWriter(semaine, true));
			event.getRepContact().lireListeDesEmployer();
			for (int i = 1; i < 41; i++) {
				semaine1.write(1 + "\r\n");
			}
			semaine1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void lectureDisponibiliteTempsEntreprise() {

	}

	public void modifierEvenement() {

	}

	public void supprimerEvenement() {

	}
	/*
	 * public void modifierEmploisDuTempsEmployer(String nom,int heure,int dure) {//
	 * heure est un chiffre entre 1 et 40 BufferedWriter emploisDuTempsEntreprise =
	 * null; lireEmploisDuTempEmployer(nom); File ancienEmploisTempEmployer= new
	 * File(".\\data\\"+nom+".txt"); File ancienEmploisTempEntreprise= new
	 * File(".\\data\\semaine1.txt");// reste � rendre le nom de semaine1 dynamique
	 * //ancienEmploisTempEmployer.delete();
	 * 
	 * try { emploisDuTempsEmployer = new BufferedWriter(new FileWriter( new
	 * File(".\\data\\"+nom+".txt"),true)); emploisDuTempsEntreprise = new
	 * BufferedWriter(new FileWriter( ancienEmploisTempEntreprise,true)); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * ancienEmploisTempEmployer.delete(); //la dure maximum d'un evenement est de 3
	 * if(EmploisDusTemps.get(heure)=="1") EmploisDusTemps.set(heure,"0"); /*
	 * if(dure==1) { EmploisDusTemps.set(heure, "0"); } else
	 * if((EmploisDusTemps.get(heure+1)=="1")&&(dure==2)) {
	 * EmploisDusTemps.set(heure, "0"); EmploisDusTemps.set(heure+1, "0"); } else
	 * if((EmploisDusTemps.get(heure+2)=="1")&&(EmploisDusTemps.get(heure+1)=="1")&&
	 * (dure==3)) { EmploisDusTemps.set(heure, "0"); EmploisDusTemps.set(heure+1,
	 * "0"); EmploisDusTemps.set(heure+2, "0"); } for(int
	 * i=0;i<EmploisDusTemps.size();i++) { try {
	 * emploisDuTempsEmployer.write(EmploisDusTemps.get(i)); } catch (IOException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); } }
	 * EmploisDusTemps.clear();
	 * 
	 * lireEmploisDuTempEmployer("semaine1");// rendre le nom dynamique
	 * 
	 * 
	 * ancienEmploisTempEntreprise.delete();
	 * 
	 * if(EmploisDusTemps.get(heure)=="1") EmploisDusTemps.set(heure,"0"); for(int
	 * i=0;i<EmploisDusTemps.size();i++) { try {
	 * emploisDuTempsEntreprise.write(EmploisDusTemps.get(i)); } catch (IOException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * try { emploisDuTempsEmployer.close(); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */
	/*
	 * public void ajouterEvenementEmploidDuTemp (Evenement event) { File
	 * semaine=new File(".\\data\\semaine1.txt"); BufferedReader jr;
	 * 
	 * try { jr = new BufferedReader (new FileReader(semaine));
	 * while((jr.readLine())!=null) { jrs.add(jr.readLine()); } } catch (IOException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * semaine.delete(); BufferedWriter contact;
	 * 
	 * try { contact = new BufferedWriter(new FileWriter(new
	 * File(".\\data\\semaine1.txt"),true));
	 * 
	 * event.getRepContact().lireListeDesEmployer();
	 * 
	 * for(int j=0;j<event.getRepContact().getListeDesEmployer().size();j++) {
	 * //event.getRepContact().getListeDesEmployer().get(i)
	 * 
	 * String nom=event.getRepContact().getListeDesEmployer().get(j); int
	 * heure=event.getReu().getHeure(); int dure=event.getReu().getDure();
	 * modifierEmploisDuTempsEmployer(nom,heure,dure);//nom -> nomDuFichier heure->
	 * i(position) dure->combien de ligne apres i
	 * 
	 * }
	 * 
	 * /*for(int i=0;i<jrs.size();i++) { if(jrs.get(i)==event.getReu().getJour()) {
	 * if(jrs.get(i)=="1") { //heure 8h=1 9h=2 ....
	 * jrs.set(i+event.getReu().getHeure(), "0"); } else { k=1; } }
	 * contact.write(jrs.get(i)); } contact.close(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */
}
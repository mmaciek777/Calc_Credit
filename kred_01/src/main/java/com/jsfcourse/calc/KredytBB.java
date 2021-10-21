package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
//import javax.enterprise.context.SessionScoped;
//import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String kred;
	private String opr;
	private String lata;
	private Double result;

	@Inject
	FacesContext ctx;	//Dependency Injection(Wstrzyknij obiekt, który chce)
	
	public String getKred() {
		return kred;
	}

	public void setKred(String kred) {
		this.kred = kred;
	}

	public String getOpr() {
		return opr;
	}

	public void setOpr(String opr) {
		this.opr = opr;
	}

	public String getLata() {
		return lata;
	}

	public void setLata(String lata) {
		this.lata = lata;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean ObliczKredyt() {
		try {
			double kred = Double.parseDouble(this.kred);
			double opr = Double.parseDouble(this.opr);
			double lata = Double.parseDouble(this.lata);

			result = kred/(lata*12) + ((kred/(lata*12)) * (opr/100));
			
			//($this->form->kwota / (($this->form->lata)*12)) + (($this->form->kwota / (($this->form->lata)*12)) * (($this->form->oprc) / 100));

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie!", null));
			
			return true;
		}catch (Exception e) {
			
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów!", null));
			
			return false;
		}
	}
	
	// Go to "showresult" if ok
		public String calc() {
			if (ObliczKredyt()) {
				return "showresult";
			}
			return null;
		}
		public String calc_AJAX() {
			if (ObliczKredyt()) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesiêczna rata kredytu wynosi: " + result + " z³", null));
			}
			return null;
		}
	
}

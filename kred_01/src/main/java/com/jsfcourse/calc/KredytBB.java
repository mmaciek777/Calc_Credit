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
	private Double kred, opr, result;
	private Integer lata;

	@Inject
	FacesContext ctx;	//Dependency Injection(Wstrzyknij obiekt, kt�ry chce)
	
	public Double getKred() { return (kred); }
	public void setKred(Double kred) {
		this.kred = kred;
	}


	public Double getOpr() { return (opr); }
	public void setOpr(Double opr) {
		this.opr = opr;
	}


	public Integer getLata() {return (lata); }
	public void setLata(Integer lata) {
		this.lata = lata;
	}


	public Double getResult() { return (result); }
	public void setResult(Double result) {
		this.result = result;
	}

	public boolean ObliczKredyt() {
		try {
			double w_kredyt;
			w_kredyt = this.kred;

			double oprocentowanie;
			oprocentowanie = this.opr;

			int i_lat;
			i_lat = this.lata;
			
			result = (w_kredyt / (i_lat * 12)) + ((w_kredyt / (i_lat * 12)) * (oprocentowanie / 100));
			
			//($this->form->kwota / (($this->form->lata)*12)) + (($this->form->kwota / (($this->form->lata)*12)) * (($this->form->oprc) / 100));

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie!", null));
			
			return true;
		}catch (Exception e) {
			
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Blad podczas przetwarzania parametr�w!", null));
			
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
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata kredytu wynosi: " + result + " zł", null));
			}
			return null;
		}
	
}

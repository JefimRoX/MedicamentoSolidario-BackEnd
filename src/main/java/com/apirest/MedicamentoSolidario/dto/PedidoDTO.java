package com.apirest.MedicamentoSolidario.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.apirest.MedicamentoSolidario.Models.Medicamento;
import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.Models.Recebimento;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.apirest.MedicamentoSolidario.controle.MedicamentoControle;
import com.apirest.MedicamentoSolidario.controle.UsuarioControle;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PedidoDTO {
	
	@Autowired
	UsuarioControle controleUsuario;
	@Autowired
	MedicamentoControle controleMedicamento;
	
	private long id;
	private String justificativa;
	private Date data;
	
	@JsonIgnore
	private Usuario usuario;
	private int idUsauruaio;
	
	@JsonIgnore
	private List<Medicamento> medicamentos;
	private int idsMedicamentos[];
	
	@JsonIgnore
	private Recebimento recebimento;
	private int idRecebimento;
	
	
	public Pedido transformarParaObjSalvar() {
		this.usuario=controleUsuario.listar(id).get();
		this.medicamentos = listarMeds(this.idsMedicamentos);
		return new Pedido(id,justificativa,data,usuario,medicamentos);
	}
	//metodo para preencher a lista de medicamentos com os ids recebidos pelo endpoint
	private List<Medicamento> listarMeds(int[] idMedicamentos2) {
		List<Medicamento> listaM = new ArrayList<Medicamento>();
		for (int i : idMedicamentos2) {
			listaM.add(controleMedicamento.listar(i).get());
		}
		return listaM;
	}
	public Pedido transformarParaObjEditar() {
		this.usuario=controleUsuario.listar(id).get();
		//this.medicamentos=controleMedicamento.listar(id).get();
		this.medicamentos = listarMeds(this.idsMedicamentos);
		return new Pedido(justificativa,data,usuario,medicamentos);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/*
	 * public List<Medicamento> getMedicamento() { return medicamentos; } public
	 * void setMedicamento(List<Medicamento> medicamento) { this.medicamentos =
	 * medicamento; }
	 */
	public Recebimento getRecebimento() {
		return recebimento;
	}
	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}

	public int getIdUsauruaio() {
		return idUsauruaio;
	}

	public void setIdUsauruaio(int idUsauruaio) {
		this.idUsauruaio = idUsauruaio;
	}

	public int[] getIdsMedicamentos() {
		return idsMedicamentos;
	}

	public void setIdsMedicamentos(int[] idMedicamento) {
		this.idsMedicamentos = idMedicamento;
	}
	public int getRecebimentoID() {
		return idRecebimento;
	}
	public void setRecebimentoID(int recebimentoID) {
		this.idRecebimento = recebimentoID;
	}

}

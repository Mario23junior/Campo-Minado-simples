package br.com.projects.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.projects.excecao.ExplosaoException;

public class Campo {
     
	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<Campo>();
	
	
   Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(linha - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;
           
		if(deltaGeral == 1 && !diagonal) {
			 vizinhos.add(vizinho);
			 return true;
		}else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}
	   // alternando Marcação
		void alternarMarcacao() {
			if(!aberto) {
				marcado = !marcado;
			}
		}
		
		// verificando se o campo esta marcado
		boolean abrir() {
			if(!aberto && !marcado) {
				aberto = true;
				
				if(minado) {
					throw new ExplosaoException();
				}
				
				if(vizihancaSegura()) {
					vizinhos.forEach(v -> v.abrir());
				}
				
				return true;
			}else {				
				return false;
			}
		}
		
		//detectando se os campos ao redor são seguros
		boolean vizihancaSegura() {
			return vizinhos.stream().noneMatch(v -> v.minado);
		}
		
		public boolean isMinado() {
			return minado;
		}
		
		void minar() {
  			minado = true;
		}
		
		public boolean isMarcado() {
			return marcado;
		}
		
		void setAberto(boolean aberto) {
			this.aberto = aberto;
		}
		
		public boolean isAberto() {
			return aberto;
		}
		
		public boolean isFechado() {
			return !isAberto();
		}

		
		 // Tabuleiro
		public int getLinha() {
			return linha;
		}
		
		public int getColuna() {
			return coluna;
		}
		
		boolean objetivoAlcancado() {
		  boolean desvendado = !minado && aberto;
		  boolean protegido = minado && marcado;
		  return desvendado || protegido;
		}
		
		long minasNaVizinhaca() {
			 return vizinhos.stream().filter(v -> v.minado).count();
		}
		
		void reiniciar() {
			aberto = false;
			minado = false;
			marcado = false;
		}
		
		
		public String toString() {
			if(marcado) {
				return "x";
			}else if(aberto && minado) {
				return "*";
			} else if(aberto && minasNaVizinhaca() > 0) {
				return Long.toString(minasNaVizinhaca());
			}else if(aberto) {
				return " ";
			} else {
				return "?";
			}
	  }
}
























package br.com.projects;

import br.com.projects.modelo.Tabuleiro;
import br.com.projects.visao.TabuleiroConsole;

public class Aplicacao {
    
	public static void main(String[] args) {
		 
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);
	}
}

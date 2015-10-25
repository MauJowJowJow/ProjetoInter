package controller;

public enum StatusScene {
		Fechado(0), Aberto(1);
		
		public int statusScene;
		StatusScene(int statusScene){
			this.statusScene = statusScene;
		}
		
		public int getStatusScene(){
			return statusScene;
		}

}

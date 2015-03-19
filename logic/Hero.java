package logic;

public class Hero extends Element{
	
	private int hero_darts = 0;
	
	public Hero() {
		set_state('h');
		int[] default_position = {1, 1};
		set_position(default_position);
	}
		
	public void kill_hero() {
		set_state(' ');
	}
	
	public void arm_hero() {
		if (get_state() == 'h') set_state('a');
		else set_state('A');
	}
	
	public void shield_hero() {
		if (get_state() == 'h') set_state('H');
		else set_state('A'); 
	}
	
	public void dart_hero() {
		hero_darts++;
	}
	
	public void undart_hero() {
		hero_darts--;
	}
	
	public int get_hero_darts() {
		return hero_darts;
	}
	
}

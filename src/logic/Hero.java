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
	
	void arm_hero() {
		if (get_state() == 'h') set_state('a');
		else set_state('A');
	}
	
	void shield_hero() {
		if (get_state() == 'h') set_state('H');
		else set_state('A'); 
	}
	
	void set_hero_darts(int h_darts) {
		hero_darts = h_darts;
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
	
	public String toString() {
		String res = get_state() + ";" + get_position()[0] + ";" + get_position()[1] + ";" + hero_darts;
		return res;
	}
	
}

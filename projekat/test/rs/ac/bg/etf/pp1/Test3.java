package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.Test3.Predmet;
import rs.ac.bg.etf.pp1.Test3.Sfera;

public class Test3 {
	//Test 303

	private static int cinilac = 4, delilac = 3;
	static public class Predmet
	{
		int tezina;
		char vrsta;

		
		void postaviTezinu(int tezina) {this.tezina = tezina;}  
		void ispis( Predmet p) { System.out.println(p.vrsta);  System.out.println(p.tezina);}
		int zapremina() { return 0;}
		int Q() {return zapremina() * tezina;}
		void postaviVrstu(char vrsta) {this.vrsta = vrsta;}
		
	}

	static public class Sfera extends Predmet
	{
		int r;
			void postaviTezinu(int tezina) {this.tezina = tezina; vrsta = 's';}
			void postaviPoluprecnik(int r) {this.r = r; postaviVrstu('s');}
			int zapremina(int z)
			{
				z = cinilac;
				z*=(r/=delilac); // 4 * (1
				return z;
			}
	}

	static class Kvadar extends Predmet
	{
		int a, b, c;
		void postaviTezinu(int tezina) {this.tezina = tezina; vrsta = 'k';}
		void postaviStranice(int a, int b, int c) {this.a = a; this.b = b; this.c = c; vrsta = 'k';}
		int zapremina(int z)
		{
			z = a;
			z*= b*=c;
			return z;
		}
	}
	
	public static void main(String[] args) {
		int zapreminaK, zapreminaS;
		int tezinaK, tezinaS;
		Predmet predmeti[]; int i; 
		Sfera s1, s2, s3; Kvadar k1, k2, k3; int t;
			
		predmeti = new Predmet[6];
		s1 = new Sfera();
		s2 = new Sfera();
		s3 = new Sfera();
		
		k1 = new Kvadar();
		k2 = new Kvadar();
		k3 = new Kvadar();

		s1.postaviPoluprecnik(1);
		s2.postaviPoluprecnik(2);
		s3.postaviPoluprecnik(3);

		k1.postaviStranice(1,2,3);
		k2.postaviStranice(2,3,4);
		k3.postaviStranice(3,4,5);
		
		predmeti[0] = s1;
		predmeti[2] = s2;
		predmeti[4] = s3;
		predmeti[1] = k1;
		predmeti[3] = k2;
		predmeti[5] = k3;

		// Dummy read t = 4;
		t = 4;
		for(i = 0; i<6; i++)
		{
			predmeti[i].postaviTezinu(t);	
		}

		i = 0;
		zapreminaS = 0;
		tezinaS = 0;
		for(; i<6;)
		{
			i++;
			if(i % 2 == 0) continue; 
			zapreminaS += predmeti[i - 1].zapremina();
			tezinaS += predmeti[i - 1].Q();
			predmeti[i - 1].ispis(predmeti[i - 1]);
		}
// tezina = t  | zapreminaS =  
// s0 {r=1, t=t, zap=} | p1 {a, b, c, t=t} | s2 {r=2, t=t} | p3 {a, b, c, t=t} | s4 {r=3, t=t} | p5 {a, b, c, t=t}
		i = 0;
		zapreminaK = 0; tezinaK = 0;
		for(; i<6;)
		{
			i++;
			if(i % 2 == 1) continue; 
			zapreminaK += predmeti[i - 1].zapremina();
			tezinaK += predmeti[i - 1].Q();
			predmeti[i - 1].ispis(predmeti[i - 1]);
		}

		System.out.println(zapreminaS);
		System.out.println(zapreminaK);
		System.out.println(tezinaS);
		System.out.println(tezinaK);
	}
}


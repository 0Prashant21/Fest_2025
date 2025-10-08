import java.util.Scanner;

public class ChefsCauldron {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int stability = 50, toxicity = 0, steps = 0;
        String last = "";  // to check combos

        System.out.println("Welcome to Chef's Cauldron!");
        System.out.println("Mix potion until Stable (70-85 stab & <20 tox)");
        System.out.println("Start → Stability=50, Toxicity=0\n");

        do {
            System.out.println("Step " + (steps+1) + " (max 12)");
            System.out.println("Pick ingredient: DRG / SLM / FTH / MNT / BLM / SUN / X=stop");
            String in = sc.nextLine().toUpperCase();

            if (in.equals("X")) { 
                System.out.println("You stopped brewing.");
                break; 
            }

            switch(in) {
                case "DRG": stability += 8; toxicity += 2; break;
                case "SLM": stability += 5; toxicity -= 2; break;
                case "FTH": stability += 10; toxicity += 5;
                            if(last.equals("SLM")) { stability+=5; toxicity-=3; 
                                System.out.println("✨ Combo! SLM→FTH synergy."); }
                            break;
                case "MNT": stability += 3; toxicity -= 5; break;
                case "BLM": stability -= 10; toxicity += 12; break;
                case "SUN": stability += 7; if(stability>80) toxicity+=4; break;
                default: System.out.println("Invalid!"); continue;
            }

            // keep values in range
            if(stability<0) stability=0;
            if(toxicity<0) toxicity=0;

            steps++;
            String status = (stability>=70 && stability<=85 && toxicity<20) ? "Almost Stable!" : "Brewing...";
            System.out.println("Status: Stab=" + stability + " Tox=" + toxicity + " → " + status);

            if(stability>=70 && stability<=85 && toxicity<20) { 
                System.out.println("Potion is Stable! Success.");
                break;
            }
            if(stability<=0 || toxicity>=100) { 
                System.out.println("Potion exploded! Failed.");
                break;
            }

            last = in; // save last ingredient
        } while(steps < 12);

        if(steps>=12) System.out.println("⏳ Steps over. Final Stab=" + stability + ", Tox=" + toxicity);
    }
}
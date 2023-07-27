import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class FlashCardApp {
    
    public static ArrayList<FlashCard> readCards( String filename ){

        String line;
        String[] data;
        
        //creates an empty list to store FlashCards
        ArrayList<FlashCard> cardList = new ArrayList<FlashCard>();

        //create a File object representing file to read
        File inFile = new File( filename );

        //try the following
        try {

            //create Scanner that reads from file with given name
            Scanner file = new Scanner( inFile );


            //while file contains more data
            while( file.hasNext() ) {
                //read the next line from the file
                line = file.nextLine();

                //split the line into tokens (hint: String split() method )
                
                data = (line.split("\\s+"));


                //create a FlashCard object using the line tokens - don't forget convert difficulty to int
                
                String question = data[0]; 
                String answer = data [1];
                int difficulty = Integer.parseInt(data[2]);                
                FlashCard flashcard = new FlashCard(question, answer, difficulty);

                //add the card to the list
            
                cardList.add(flashcard);
            }
            //close the file
            file.close();
        }catch( FileNotFoundException f ){
            System.out.println("file cannot be opened");
        }
        //return the ArrayList containing FlashCards from the file
        return cardList;
    }

    public static void bubbleSort(ArrayList<FlashCard> cards) {
        
        boolean sorted = false;
        
        for (int i = 0; i < cards.size() && !sorted; i++) {

            sorted = true;
            for (int j = 0; j < cards.size() -i-1 ; j++ ) {

                if (((cards.get(j).getQuestion()).compareTo(cards.get(j+1).getQuestion()) < 0) ) {

                    sorted = false;
                    FlashCard temp = cards.get(j);
                    cards.set(j, (cards.get(j+1)));
                    cards.set(j+1, temp);

                }
            }
        }
    }

    public static void main(String[] args) {
        
        ArrayList<FlashCard> flashCards = readCards("turkish_english_words.txt");

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of flash cards to generate: ");
        int number_of_cards = scan.nextInt();
        scan.nextLine();

        Random rand = new Random();
        ArrayList<FlashCard> randomFlashCards = new ArrayList<>();

        for (int i = 0 ; i < number_of_cards; i++ ) {

            int random_num = rand.nextInt(flashCards.size()+1);
            if (randomFlashCards.indexOf(flashCards.get(random_num)) == -1) {
                randomFlashCards.add(flashCards.get(random_num));
            }

        }

        bubbleSort(randomFlashCards);

        int correct_answer = 0;

        ArrayList<FlashCard> wrong_answers = new ArrayList<>();

        for (FlashCard card: randomFlashCards) {

            //card.reverseCard();

            System.out.println(card.showFlashCardQuestion());

            System.out.print("Enter your guess: ");
            String input_answer = scan.nextLine();
            if (input_answer.equals(card.getAnswer())) {
                correct_answer++;
                System.out.println("Correct!");
            }
            else {

                System.out.println("Wrong answer!");
                wrong_answers.add(card); 
                System.out.println("Let's see the correct answer:");
                System.out.println(card.showFlashCardAnswer());
            }
        }

        System.out.println("Your score: " + correct_answer + " / " + number_of_cards);
        System.out.println("Words you need to review: ");
        System.out.println(wrong_answers.toString());

        scan.close();
    }
}
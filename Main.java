public class Main {
    public static void main(String[] args) {


       //Creating bit vector

        BitVector bitVector1 = new BitVector();
        BitVector bitVector2 = new BitVector(9);
////      FileNotFoundException
//      BitVector bitVector3 = new BitVector("non_existing_file_name.txt");


        //Check if bit vector is empty
        System.out.println("bitvector1 is empty: " + bitVector1.isEmpty());
        System.out.println("bitvector2 is empty: " + bitVector2.isEmpty());
        System.out.println();


        //Getting initial size of bit vector
        System.out.println("Size of bitvector1 is: " + bitVector1.getInitialSize());
        System.out.println("Size of bitvector2 is: " + bitVector2.getInitialSize());
        System.out.println();


        //Setting values
        bitVector2.set(1);
        bitVector2.set(8);
////      BitVectorOutOfBoundsException (Invalid index for setting)
//      bitVector2.set(9);
//      bitVector2.set(-2);
//      bitVector1.set(0);


        //Setting all values
        bitVector2.setAll();
////      BitVectorOutOfBoundsException (bit vector is empty)
//        bitVector1.setAll();


        //Resetting values
        bitVector2.reset(7);
        bitVector2.reset(1);
////      BitVectorOutOfBoundsException (Invalid index for resetting)
//      bitVector2.set(9);
//      bitVector2.reset(-18);
//      bitVector1.set(0);


        //Resetting all values
        bitVector2.resetAll();
////      BitVectorOutOfBoundsException (bit vector is empty)
//        bitVector1.resetAll();


        //Check if bit is set
        bitVector2.set(5);
        System.out.println(0 + " index of bitvector2 is set: " + bitVector2.isSet(0));
        System.out.println(5 + " index of bitvector2 is set: " + bitVector2.isSet(5));
////      BitVectorOutOfBoundsException
//      System.out.println(0 + " index of bitvector1 is set: " + bitVector1.isSet(0));


        //writing in file
        bitVector2.writeInFile("bitVector2.txt");
////      BitVectorOutOfBoundsException (bit vector is empty)
//      bitVector1.writeInFile("empty.txt");


        //Extending bit vector by given quantity of bits
        bitVector2.extendBitVector(4);
        bitVector1.extendBitVector(20);
//        Invalid number for extending bit vector
        bitVector1.extendBitVector(-20);


        //Calculating decimal value
        bitVector2.set(12);
        bitVector1.set(2);
        bitVector1.set(10);
        bitVector1.writeInFile("bitVector1.txt");
        bitVector2.writeInFile("bitVector2.txt");
        //positive result
        System.out.println("Decimal value of bitvector1 is: " + bitVector1.getDecimalValue());
        //negative result
        System.out.println("Decimal value of bitvector2 is: " + bitVector2.getDecimalValue());

        bitVector2.setAll();
        System.out.println("Decimal value of bitvector2 is: " + bitVector2.getDecimalValue());


        /*Example of reading from file and writing back
          Create bitVector3.txt
          In bitVector3.txt: __00__111_01__00010 -> 0011_10100011*/

//        BitVector bitVector3 = new BitVector("bitVector3.txt");
//        bitVector3.set(0);
//        bitVector3.writeInFile("bitVector3.txt");


    }
}



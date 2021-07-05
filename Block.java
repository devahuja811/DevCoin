import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data; //our data will be a simple message.
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;

    //Block Constructor.
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calcHash(); //Making sure we do this after we set the other values.
    }

    //Calculate new hash based on blocks contents
    public String calcHash() {
        return StringUtil.applySha256(
                previousHash +
                        timeStamp +
                        nonce +
                        data
        );
    }

    //Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        String target = StringUtil.getDifficultyString(difficulty); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calcHash();
        }
        System.out.println("Found Block : " + hash);
    }

}
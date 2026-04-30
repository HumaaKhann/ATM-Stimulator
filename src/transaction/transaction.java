package transaction;

import model.transactionmodel;


public class transaction {
    // sende_money
    //withdraw_money
    //deposit_money
    //it will create trascation and store it in the file tracnstip.txt

    int amount;
    transactionmodel transactionmodel;


    transaction( transactionmodel transactionmodel,int amount){
        // create a transaction and store it in the file tracnstip.txt
        super();
        this.amount = amount;
        this.transactionmodel = transactionmodel;

    }   
    
    void send_money(){
        // create a transaction and store it in the file tracnstip.txt

        
        double sender_balance  = 0;
        int sender_Id = transactionmodel.getSenderId();

        UserSearch userSearch = new UserSearch();
        sender_balance = userSearch.findUserById(sender_Id).getBalance();

        // here we will get the sender balance from the file and check if the sender has enough balance to send money

        
        if(amount>0 && transactionmodel.getSenderId() != transactionmodel.getReceiverId() && sender_balance >= amount){

            //1. deduct amount from sender
            //2. add amount to receiver
            //3. create a transaction and store it in the file tracnstip.txt

            System.out.println("Transaction successful");
      }
    

    }



}


package transaction;

import model.transactionmodel;
import utils.*;


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

        
        double sender_balance;
        double receiver_balance ;
        int sender_id = this.transactionmodel.getSenderId();
        int receiver_id = this.transactionmodel.getReceiverId();

        UserSearch userSearch = new UserSearch();
        sender_balance = userSearch.findUserById(sender_id).getBalance();
        receiver_balance = userSearch.findUserById(receiver_id).getBalance();
        

        // here we will get the sender balance from the file and check if the sender has enough balance to send money

        ChangeBalanceById changeBalanceById = new changeBalanceById();

        
        if(amount>0 && transactionmodel.getSenderId() != transactionmodel.getReceiverId() && sender_balance >= amount){



            //1. deduct amount from sender

            changeBalanceById.changeBalanceById(transactionmodel.getSenderId(), sender_balance - this.amount);
            


            //2. add amount to receiver

            changeBalanceById.changeBalanceById(transactionmodel.getReceiverId(), receiver_balance + this.amount);


            //3. create a transaction and store it in the file tracnstip.txt

            

            System.out.println("Transaction successful");
      }
    

    }



}


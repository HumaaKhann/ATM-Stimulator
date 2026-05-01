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

    UserSearch userSearch = new UserSearch();
    SaveTrascationIntoFile saveTrascationIntoFile = new SaveTrascationIntoFile(this.transactionmodel);
    ChangeBalanceById changeBalanceById = new ChangeBalanceById();
    
    

    void send_money(){
        // create a transaction and store it in the file tracnstip.txt

        transactionmodel.setAmount(amount);
        double sender_balance;
        double receiver_balance ;
        int sender_id = this.transactionmodel.getSenderId();
        int receiver_id = this.transactionmodel.getReceiverId();

        



        sender_balance = userSearch.findUserById(sender_id).getBalance();
        receiver_balance = userSearch.findUserById(receiver_id).getBalance();
        

        // here we will get the sender balance from the file and check if the sender has enough balance to send money

        

        
        if(amount>0 && transactionmodel.getSenderId() != transactionmodel.getReceiverId() && sender_balance >= amount){



            //1. deduct amount from sender

            changeBalanceById.changeBalanceById(transactionmodel.getSenderId(), sender_balance - this.amount);

            //2. add amount to receiver

            changeBalanceById.changeBalanceById(transactionmodel.getReceiverId(), receiver_balance + this.amount);

            //3. create a transaction and store it in the file tracnstip.txt

            saveTrascationIntoFile.SaveTransaction();

            System.out.println("Transaction successful");
      }  else{
            System.out.println("Transaction failed");
        }
    }


    //TODOfor huma khan 

    void widthdraw_money(){
        // create a transaction and store it in the file tracnstip.txt
        int sender_id = this.transactionmodel.getSenderId();
        double sender_balance = 0;
		try {
			sender_balance = userSearch.findUserById(sender_id).getBalance();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
            if(amount>0 && sender_balance >= amount){
               changeBalanceById.changeBalanceById(transactionmodel.getSenderId(), sender_balance - this.amount);
                saveTrascationIntoFile.SaveTransaction();
            } else{
                System.out.println("Transaction failed");
        }
    }

    void deposit_money(){
        // create a transaction and store it in the file tracnstip.txt
        int reciever_id = this.transactionmodel.getReceiverId();
        double sender_balance = 0;
        try {
            sender_balance = userSearch.findUserById(reciever_id).getBalance();
        } catch (Exception e) {
        
            e.printStackTrace();
        }
        
        if(amount>0){
            changeBalanceById.changeBalanceById(transactionmodel.getReceiverId(), sender_balance + this.amount);
            saveTrascationIntoFile.SaveTransaction();
        }   
     }

}


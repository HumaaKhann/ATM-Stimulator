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

<<<<<<< HEAD
        
=======
        transactionmodel.setAmount(amount);
>>>>>>> 45597fd (complete send_money function)
        double sender_balance;
        double receiver_balance ;
        int sender_id = this.transactionmodel.getSenderId();
        int receiver_id = this.transactionmodel.getReceiverId();

        UserSearch userSearch = new UserSearch();
<<<<<<< HEAD
=======
        SaveTrascationIntoFile saveTrascationIntoFile = new SaveTrascationIntoFile(this.transactionmodel);



>>>>>>> 45597fd (complete send_money function)
        sender_balance = userSearch.findUserById(sender_id).getBalance();
        receiver_balance = userSearch.findUserById(receiver_id).getBalance();
        

        // here we will get the sender balance from the file and check if the sender has enough balance to send money

        ChangeBalanceById changeBalanceById = new changeBalanceById();

        
        if(amount>0 && transactionmodel.getSenderId() != transactionmodel.getReceiverId() && sender_balance >= amount){



            //1. deduct amount from sender

            changeBalanceById.changeBalanceById(transactionmodel.getSenderId(), sender_balance - this.amount);
<<<<<<< HEAD
            

=======
>>>>>>> 45597fd (complete send_money function)

            //2. add amount to receiver

            changeBalanceById.changeBalanceById(transactionmodel.getReceiverId(), receiver_balance + this.amount);

<<<<<<< HEAD

            //3. create a transaction and store it in the file tracnstip.txt

            
=======
            //3. create a transaction and store it in the file tracnstip.txt

            saveTrascationIntoFile.SaveTransaction();
>>>>>>> 45597fd (complete send_money function)

            System.out.println("Transaction successful");
      }  else{
            System.out.println("Transaction failed");
        }
    }


    //TODOfor huma khan 

    void widthdraw_money(){
        // create a transaction and store it in the file tracnstip.txt
    }

    void deposit_money(){
        // create a transaction and store it in the file tracnstip.txt
    }



}


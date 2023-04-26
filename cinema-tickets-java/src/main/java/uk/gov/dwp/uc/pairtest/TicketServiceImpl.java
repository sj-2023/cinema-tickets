package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) 
    {
        if(accountId < = 0 ){
            //Throw exception - not a avalid account
        }

        boolean hasAdult = false;
        int totalPrice = 0;
        int totalTickets = 0; 
        // Loop through each ticket types
        for (TicketTypeRequest tickets : ticketTypeRequests)
        {
            var type =  tickets.getTicketType();
            var numberOfTickets = tickets.getNoOfTickets();

            //check an adult exists in the request
            if(type == Type.ADULT)
            {
                hasAdult = true; 
            }
            
            //Get the total number of tickets to check if it has exceeded the max allowed
            totalTickets = totalTickets + numberOfTickets
            if(numberOfTickets > 0)
            {
                if (type == Type.ADULT)
                {
                    totalPrice = totalPrice +  (numberOfTickets * 20)
                }
                else if (type == Type.CHILD)
                {
                    totalPrice = totalPrice +  (numberOfTickets * 10)
                }
            }
        }

        if(numberOfTickets > 20 ){
            //throw exception 
        }

        if(hasAdult == false){
            //throw exception 
        }

        if(numberOfTickets <= 20 && hasAdult){
            //reserve seat 
            //Make payment 
        }
        
    }

}

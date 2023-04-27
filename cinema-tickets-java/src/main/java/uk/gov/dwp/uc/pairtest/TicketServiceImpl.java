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
            throw new InvalidPurchaseException("Invalid accountId");
        }

        boolean hasAdult = false;
        int totalPrice = 0;
        int totalTickets = 0; 
        int maximumTickets = 0;
        // Loop through each ticket types
        for (TicketTypeRequest tickets : ticketTypeRequests)
        {
            var type =  tickets.getTicketType();
            var numberOfTickets = tickets.getNoOfTickets();
            //
            maximumTickets = maximumTickets + numberOfTickets;
            
            if(numberOfTickets > 0)
            {
                //Only Adult and Child need to pay for a ticket and seat allocated
                if (type == Type.ADULT)
                {
                    hasAdult = true; //An adult exists in the request
                    totalTickets = totalTickets + numberOfTickets
                    totalPrice = totalPrice +  (numberOfTickets * 20)
                }
                else if (type == Type.CHILD)
                {
                    totalTickets = totalTickets + numberOfTickets
                    totalPrice = totalPrice +  (numberOfTickets * 10)
                }
            }
        }

        if(maximumTickets > 20 ){
            //throw exception 
            throw new InvalidPurchaseException("Only a maximum of 20 tickets that can be purchased at a time");
        }

        if(hasAdult == false){
            //throw exception 
            throw new InvalidPurchaseException("Child and Infant tickets cannot be purchased without purchasing an Adult ticket");
        }

        if(numberOfTickets <= 20 && hasAdult){
            //reserve seat 
            SeatReservationService reservationService = new SeatReservationServiceImpl();
            reservationService.reserveSeat(accountId,numberOfTickets )
            //Make payment 
            TicketPaymentService paymentService = new TicketPaymentServiceImpl();
            paymentService.makePayment(accountId, totalPrice);
        }
        
    }

}

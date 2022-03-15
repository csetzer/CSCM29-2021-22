// SPDX-License-Identifier: LGPL-3.0
pragma solidity ^0.8.12;

contract Election {

    address administrator;

    // numberVotes is a state variable that
    // stores for each address the number of votes it has left
    //   after modifications due to delegation, votes granted and
    //   after subtracting votes the address has used for voting
    mapping (address => uint) public numberVotes;

    // votesReceived is a state variable determining the number of votes
    //    received by each candidate.
    // It will initialised by the constructor to an array of length 
    //    number of candidates
    uint[] public votesReceived;

    // The number of candidates is the length of the array votesReceived
    function numberCandidates () public view returns(uint){
        return votesReceived.length;
    }

    // Task 5.1: Implement function isValidateCandidate
    //
    // It should determine  whether _candidate is a valid candidate
    // _candidate is a valid candidate if it's number is less than the number
    //    of candidates
    function isValidCandidate (uint _candidate) public view returns (bool){
        // Should be replaced by the correct implementation
        return true;
    }

    // Determine the number of votes of msg.sender
    function numberVotesOfSender () public view returns (uint){
        return numberVotes[msg.sender];
    }

    // Task 5.2
    // Create a constructor for an election with _numCandidates many candidates
    // The number of candidates needs to be > 0
    //    Use require(condition,string) to check for such conditiosn
    // It should initialise the array votesReceived to an array of length _numCandidates
    // It should set the administrator to the sender of the message
    
    constructor (uint _numCandidates) {
    }

    // Task 5.3
    // Implement function giveVote
    // It should require that the sender is administrator
    // If yes the number of votes for the _voter should be incremented by 1
    function giveVote(address _voter) public {
    }

    // Task 5.4
    // Implement funtion delegate
    // It should delegate 1 vote from the sender to the voter _to.
    // Requires that msg.sender has at least one vote to delegate
    // Effect should be that the sender has one vote less and the
    //   address _to  has one more vote to give
    function delegate(address _to) public {
    }

    // Task 5.5
    // Implement vote
    //
    // it should give one vote by the sender to the candate
    //   requires that the sender has at least one vote
    //   and that _candidate is a valid candidate
    // Afterwards the votes of the candidate should be incremented by 1
    //   and the number of votes of the sender should be decremented by 1
    function vote(uint _candidate) public {
    }

    // Task 5.6
    // Implement the function winningCandidate() which determines the 
    //      the winning candidate
    // it will be the one who has received the most most
    // if there is a draw, in this simple contract  the one with the
    //    minimal number wins.
    // In a proper election something more complex needs to happen such as
    //    a chairman deciding the candidate.
    function winningCandidate() public view returns (uint _winningCandidate) {
        // Should be replaced by the correct implementation
        return 0;
    }
}

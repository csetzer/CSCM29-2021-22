// SPDX-License-Identifier: LGPL-3.0
pragma solidity ^0.8.12;
import "./Election.sol";
import "./Voter.sol";

contract TestElection {
    // election will initialised by an element of Election
    // its address will be the administator
    Election election;

    // Voter is a list of voters
    // They will be initialised by calling the constructor of Voter
    //  with argument the election
    // Each Voter will serve as a voter in the election
    Voter [] voter;

    // createTestElection creates an element of Election for _numCandidates many candidates.
    // The election element (or its address) is the administrator of the election
    // Then createTestElection creates _numVotes many voters
    // and gives each of them one vote.
    // When called each voter can delegate its vote and can vote
     
    function createTestElection(uint _numCandidates,uint _numVoters) public {
        election = new Election(_numCandidates);
        voter = new Voter[](_numVoters);
        for (uint i = 0; i < _numVoters; i++ ){
            Voter _voter = new Voter(election);
            voter[i] = _voter;
            election.giveVote(address(_voter));
        }
    }


    // Voter _from (given by its index) delegates it's vote to _to
    //       (given by an index)
    function delegate(uint _from,uint _to) public {
        voter[_from].delegate(address(voter[_to]));
    }

    // Voter _voter (given by an index) votes for candidate _candidate
    function vote(uint _voter,uint _candidate) public {
        voter[_voter].vote(_candidate);
    }


    // Determine the number of voters
    function numberVoters () public view returns (uint){
        return voter.length;
    }

    // Determine for a voter the number of votes
    function numberVotes(uint _voter) public view returns (uint){
        return voter[_voter].numberOfVotes();
    }

    // Message emitted to show how many votes a voter has (given by an index)
    event numberOfVotesPerVoter(uint voter,uint numberVotes);

    // Function emitting  events showing the number of votes for each voter
    function showNumberOfVotesPerVoter () public{
        for (uint i=0; i < voter.length; i++)
            emit numberOfVotesPerVoter(i,numberVotes(i));
    }

    // Message emitted to show how many votes for a candidate are shown
    event numberOfVotesForCandidate(uint candidate,uint numberVotes);

    // Function emitting  events showing the number of votes received for each candidate
    function showNumberOfVotesForCandidates () public{
        for (uint i=0; i < election.numberCandidates(); i++)
            emit numberOfVotesForCandidate(i,election.votesReceived(i));
        
    }

    // Event for displaying a string descripton for a number, and the number
    event message(string _info,uint _number);

    // Event for describing an action given as a string
    event actionDescription(string action);

    // Task 5.6
    // Create an experimental election using the operations above
    //   as described in the lab sheet
    function experiment0 () public {
    }
}

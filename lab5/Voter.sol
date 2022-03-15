// SPDX-License-Identifier: LGPL-3.0
pragma solidity ^0.8.12;
import "./Election.sol";


// Voter is a contract which simulates a voter in an election
// It will be initialised with the constructor by referring to an election.
// It needs to be given at least one vote, by the administrator of that election.
// Then it can delegate its vote, and vote

contract Voter {

    Election election;

    constructor (Election _election) {
        election = _election;
    }

    function numberCandidates () public view returns (uint){
        return election.numberCandidates();
    }

    function numberOfVotes () public view returns (uint){
        return election.numberVotesOfSender();
    }

    function delegate (address _to) public {
        election.delegate(_to);
    }

    function vote(uint _candidate) public {
        election.vote(_candidate);
    }
}

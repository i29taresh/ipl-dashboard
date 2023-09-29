import React, { useEffect, useState } from 'react'
import MatchDetailsCard from '../components/MatchDetailsCard'
import { useParams } from 'react-router-dom/cjs/react-router-dom.min';

const MatchPage = () => {
   const [matches, setMatches] = useState([]);
   const {teamName, year} = useParams();
   useEffect(() => {
    const fetchMatches = async() => {
        const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
        const data = await response.json();
        setMatches(data);
    }
    fetchMatches();
   }, [teamName, year]);
  return (
    <div className='Matchpage'>
        <h1>Match Page</h1>
        {
            matches.map(match => <MatchDetailsCard teamName={teamName} match={match} /> )
        }
    </div>
  )
}

export default MatchPage
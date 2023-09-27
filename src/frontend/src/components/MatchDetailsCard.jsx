import React from 'react'

const MatchDetailsCard = ({match}) => {
  return (
    <div className="MatchDetailsCard">
        <h3>Latest match</h3>
        <h4>Match Details</h4>
        <h4>{match.team1} vs {match.team2}</h4>
    </div>
  )
}

export default MatchDetailsCard
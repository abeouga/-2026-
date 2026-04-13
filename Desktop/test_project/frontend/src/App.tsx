import { useState, useEffect } from 'react'

function App() {
  const [message, setMessage] = useState<string>('Loading...')

  useEffect(() => {
    // Backend API connection
    fetch('http://localhost:8080/api/hello')
      .then(response => response.json())
      .then(data => {
        // Receipt of JSON and storage in state
        setMessage(data.message)
      })
      .catch(error => {
        console.error('Error fetching data:', error)
        setMessage('Error loading message')
      })
  }, [])

  return (
    <div>
      <h1>Backend Response:</h1>
      <p>{message}</p>
    </div>
  )
}

export default App

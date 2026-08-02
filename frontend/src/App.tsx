import { useEffect, useState } from "react";
import { getHealth } from "./services/api";

function App() {

  const [status, setStatus] = useState("");

  useEffect(() => {
    getHealth()
      .then(response => {
        setStatus(response);
      });
  }, []);

  return (
    <div>
      <h1>Document RAG Assistant</h1>

      <p>
        Backend Status: {status}
      </p>
    </div>
  );
}

export default App;
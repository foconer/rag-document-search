import DocumentUpload from "./components/DocumentUpload";
import Search from "./components/Search";

function App() {
    return (
        <div>
            <h1>Document RAG Assistant</h1>

            <DocumentUpload />
            <Search />
        </div>
    );
}

export default App;
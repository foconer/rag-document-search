import { useState } from "react";
import { uploadDocument } from "../services/documentService";
import "./DocumentUpload.css";

function DocumentUpload() {

    const [selectedFile, setSelectedFile] = useState<File | null>(null);
    const [message, setMessage] = useState("");
    const [uploading, setUploading] = useState(false);

    const handleUpload = async () => {

        if (!selectedFile) {
            setMessage("Please select a PDF.");
            return;
        }

        try {
            setUploading(true);
            setMessage("");

            const result = await uploadDocument(selectedFile);

            setMessage(
                `✅ ${result.fileName} uploaded successfully. Pages: ${result.pages}, Characters: ${result.characters}`
            );

        } catch (error) {

            console.error(error);
            setMessage("❌ Upload failed.");

        } finally {
            setUploading(false);
        }
    };


    return (
        <div className="upload-card">

            <h2>
                📄 Upload Document
            </h2>

            <p className="upload-description">
                Upload a PDF to extract text and create searchable embeddings.
            </p>


            <label className="file-drop-zone">

                <input
                    type="file"
                    accept=".pdf"
                    onChange={(e) =>
                        setSelectedFile(
                            e.target.files?.[0] ?? null
                        )
                    }
                />


                {selectedFile ? (
                    <div>
                        <div className="file-icon">
                            📑
                        </div>

                        <strong>
                            {selectedFile.name}
                        </strong>

                        <p>
                            Ready to upload
                        </p>
                    </div>
                ) : (
                    <div>
                        <div className="upload-icon">
                            ⬆
                        </div>

                        <strong>
                            Click to select PDF
                        </strong>

                        <p>
                            Supported format: PDF
                        </p>
                    </div>
                )}

            </label>


            <button
                onClick={handleUpload}
                disabled={!selectedFile || uploading}
            >
                {uploading
                    ? "Processing..."
                    : "Upload Document"}
            </button>


            {message && (
                <div className="upload-message">
                    {message}
                </div>
            )}

        </div>
    );
}

export default DocumentUpload;
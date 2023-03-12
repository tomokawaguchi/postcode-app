import { useState } from "react";
import "./App.css";
import RegisterSuburbForm from "./components/RegisterSuburbForm/RegisterSuburbForm";
import SignupForm from "./components/SignupForm/SignupForm";
import SearchSection from "./containers/SearchSection/SearchSection";

const App = () => {
	const [isSignedIn, setIsSignedIn] = useState(false);
	const [isEditor, setIsEditor] = useState(false);
	const [isValid, setIsValid] = useState(true);
	const [userInput, setUserInput] = useState({ userName: "", password: "" });
	const [createdSuburb, setCratedSuburb] = useState({ suburbName: "", postcode: 200 });
	const [isCreated, setIsCreated] = useState(false);

	const handleOnClick = () => {
		const { userName, password } = userInput;
		const concatCredential = userName + password;

		switch (concatCredential) {
			case "editoreditor123":
				setIsValid(true);
				setIsEditor(true);
				setIsSignedIn(true);
			case "viewerviewer123":
				setIsValid(true);
				setIsSignedIn(true);
			default:
				setIsValid(false);
		}
	};

	return (
		<div className="App">
			<main>
				<h1>Suburb Search</h1>
				{!isSignedIn ? (
					<>
						<p>There are two user types: Editor and Viewer</p>
						<p>Please refer to the README file for the credentials.</p>
						<SignupForm handleOnClick={handleOnClick} userInput={userInput} setUserInput={setUserInput} />
						{!isValid && <p className="error-message">Please enter a valid user credentials</p>}
					</>
				) : (
					<>
						<SearchSection />
						{isEditor && (
							<div>
								<h2>Register new suburb</h2>

								{isCreated ? (
									<>
										<p>You have successfully registered the following suburbs:</p>
										<p>Suburb Name: {createdSuburb.suburbName.toUpperCase()}</p>
										<p>Postcode: {createdSuburb.postcode}</p>
										<button type="button" onClick={() => setIsCreated(false)}>
											Register new suburb
										</button>
									</>
								) : (
									<RegisterSuburbForm setCratedSuburb={setCratedSuburb} setIsCreated={setIsCreated} />
								)}
							</div>
						)}
					</>
				)}
			</main>
		</div>
	);
};

export default App;

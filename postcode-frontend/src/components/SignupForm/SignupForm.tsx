import { useState } from "react";
import { validationDetails } from "../../types";

import styles from "./SignupForm.module.scss";

interface SignupFormProps {
	handleOnClick: () => void;
	userInput: validationDetails;
	setUserInput: (input: validationDetails) => void;
}

const SignupForm = ({ handleOnClick, userInput, setUserInput }: SignupFormProps) => {
	const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const input = { ...userInput, [e.target.name]: e.target.value };
		setUserInput(input);
	};

	return (
		<section className={styles.SignupFormSec}>
			<form onSubmit={(e) => e.preventDefault()} className={styles.SignupForm}>
				<div className={styles.LabelInputWrapper}>
					<label htmlFor="userName">User Name: </label>
					<input id="userName" type="text" name="userName" onChange={handleOnChange} />
				</div>
				<div className={styles.LabelInputWrapper}>
					<label htmlFor="password">Password: </label>
					<input id="password" type="text" name="password" onChange={handleOnChange} />
				</div>
				<button onClick={handleOnClick}>Sign up</button>
			</form>
		</section>
	);
};

export default SignupForm;

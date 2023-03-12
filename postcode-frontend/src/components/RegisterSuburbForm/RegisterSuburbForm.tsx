import { useState } from "react";
import axios from "axios";
import { encode } from "base-64";
import { defaultInput, validationDetails } from "../../types";
import styles from "./RegisterSuburbForm.module.scss";

let username = "editor";
let password = "editor123";

const headers = {
	Authorization: `Basic ${encode(`${username}:${password}`)}`,
	"Access-Control-Allow-Origin": "*",
};

interface RegisterSuburbFormProps {
	setCratedSuburb: (result: defaultInput) => void;
	setIsCreated: (b: boolean) => void;
}

const RegisterSuburbForm = ({ setCratedSuburb, setIsCreated }: RegisterSuburbFormProps) => {
	const [newSuburb, setNewSuburb] = useState({
		suburbName: "",
		postcode: 200,
	});

	const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setNewSuburb({ ...newSuburb, [e.target.name]: e.target.value });
	};

	const handleRegister = (e: React.SyntheticEvent) => {
		e.preventDefault();
		axios.post("http://localhost:8080/suburbs/create", newSuburb, { headers }).then((res) => {
			if (res.status == 201) {
				const created: defaultInput = { suburbName: res.data.suburbName, postcode: res.data.postcode };

				setCratedSuburb(created);
				setIsCreated(true);
			}

			console.log(res);
		});
	};

	return (
		<form onSubmit={handleRegister}>
			<div className="label-input-wrapper">
				<label htmlFor="suburbName">Suburb Name: </label>
				<input id="suburbName" name="suburbName" type="text" onChange={handleOnChange} />
			</div>
			<div className="label-input-wrapper">
				<label htmlFor="postcode">Postcode: </label>
				<input id="postcode" name="postcode" type="number" min={200} max={9999} onChange={handleOnChange} />
			</div>
			<button className={styles.RegisterSuburbForm_button}>Register New Suburb</button>
		</form>
	);
};

export default RegisterSuburbForm;

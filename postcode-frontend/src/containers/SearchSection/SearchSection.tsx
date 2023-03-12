import { useEffect, useState } from "react";
import axios from "axios";
import { encode } from "base-64";
import Search from "../../components/Search/Search";
import SearchResult from "../../components/SearchResult/SearchResult";
import styles from "./SearchSection.module.scss";
import { defaultData } from "../../types";
let username = "editor";
let password = "editor123";

const SearchSection = () => {
	const [allSuburbs, setAllSuburbs] = useState<defaultData[]>([]);
	const [keyword, setKeyword] = useState("");
	const [suburbs, setSuburbs] = useState<defaultData[]>([]);
	const [showResult, setShowResult] = useState(false); // Control the visibility of results

	useEffect(() => {
		axios({
			method: "get",
			url: "http://localhost:8080/suburbs",
			headers: {
				Authorization: `Basic ${encode(`${username}:${password}`)}`,
				"Access-Control-Allow-Origin": "*",
			},
		}).then((res) => {
			setAllSuburbs(res.data);
		});
	}, []);

	const handleClick = () => {
		let filtered = allSuburbs.filter((suburb) => suburb.postcode.toString() == keyword || suburb.suburbName.trim() == keyword.toLowerCase());
		setSuburbs(filtered);
		setShowResult(true);
	};

	return (
		<section className={styles.SearchSection}>
			<Search setKeyword={setKeyword} handleClick={handleClick} showResult={showResult} setShowResult={setShowResult} />
			{showResult && <SearchResult suburbs={suburbs} keyword={keyword} />}
		</section>
	);
};

export default SearchSection;

import styles from "./Search.module.scss";

interface SearchProps {
	setKeyword: (k: string) => void;
	handleClick: () => void;
	showResult: boolean;
	setShowResult: (b: boolean) => void;
}

const Search = ({ setKeyword, handleClick, showResult, setShowResult }: SearchProps) => {
	const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setKeyword(e.target.value);
		if (showResult) setShowResult(false);
	};

	return (
		<div className={styles.Search}>
			<input type="text" onChange={handleOnChange} placeholder="Type in suburb name or postcode" />
			<button className={styles.SearchButton} onClick={handleClick}>
				Search
			</button>
		</div>
	);
};

export default Search;

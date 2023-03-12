import styles from "./SearchResult.module.scss";

interface SearchResultProps {
	suburbs: { id: number; suburbName: string; postcode: number }[];
	keyword: string;
}

const SearchResult = ({ suburbs, keyword }: SearchResultProps) => {
	return (
		<section className={styles.SearchResult}>
			{keyword.length > 0 && <h2>Search result for "{keyword}"</h2>}
			<ul className={styles.ResultList}>
				{suburbs &&
					suburbs.length > 0 &&
					suburbs
						.sort((a, b) => +a.postcode - +b.postcode) // sort by ascending postcode
						.map((suburb) => (
							<li className={styles.ResultItem} key={suburb.id}>
								<span className={styles.ResultItem__Postcode}>{suburb.postcode}</span>
								<span className={styles.ResultItem__Suburb}>{suburb.suburbName.toUpperCase()}</span>
							</li>
						))}
				{keyword && suburbs.length == 0 && <h3>There's no suburb found. Try again for other postcode or suburb name!</h3>}
			</ul>
		</section>
	);
};

export default SearchResult;

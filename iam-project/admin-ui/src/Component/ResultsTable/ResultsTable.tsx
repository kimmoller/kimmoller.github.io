import styles from "./ResultsTable.module.css";

type ButtonAttributes = {
  onClick: (id: string) => void;
};

type HeaderCell = {
  key: string;
  headerName: string;
  isButton?: boolean;
  buttonAttributes?: ButtonAttributes;
};

type Row = {
  [key: string]: string;
};

type Props = {
  header: Array<HeaderCell>;
  data: Array<Row>;
};

const ResultsTable = (props: Props) => {
  return (
    <table className={styles.resultsTable}>
      <thead>
        <tr className={styles.headerRow}>
          {props.header.map((cell) => {
            return <th key={`${cell.key}-header`}>{cell.headerName}</th>;
          })}
        </tr>
      </thead>
      <tbody>
        {props.data.map((row) => {
          const rowElement = props.header.map((cell) => {
            if (cell.isButton) {
              return (
                <td className={styles.buttonData} role='button' onClick={() => cell.buttonAttributes?.onClick(row.id)}>
                  {row[cell.key]}
                </td>
              );
            }
            return <td>{row[cell.key]}</td>;
          });
          return <tr className={styles.dataRow}>{rowElement}</tr>;
        })}
      </tbody>
    </table>
  );
};

export default ResultsTable;

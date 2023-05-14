import React, { useState } from "react";
import TextInput from "../../Component/TextInput/TextInput";
import ResultsTable from "../../Component/ResultsTable/ResultsTable";
import * as mockIdentites from "../../mockData/identitySearchData.json";
import { Identity } from "../../Types/Identity.types";
import styles from "./IdentitySearch.module.css";

const IdentitySearch = () => {
  const [identitySearchValue, setIdentitySearchValue] = useState("");
  const [identities, setIdentities] = useState<Array<Identity>>();

  const onIdentitySearchKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      setIdentities(mockIdentites.identities);
    }
  };

  const onIdentitySearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setIdentitySearchValue(event.target.value);
  };

  const onIdentityClick = (id: string) => {
    console.log(id);
    console.log(identities?.filter((identity) => identity.id === id));
  };

  const getResultsTableHeaderRow = () => {
    const headerRow = [
      {
        key: "lastName",
        headerName: "Last name",
        isButton: true,
        buttonAttributes: { onClick: (id: string) => onIdentityClick(id) },
      },
      { key: "firstName", headerName: "First name" },
      { key: "email", headerName: "Email" },
    ];
    return headerRow;
  };

  const getResultsData = () => {
    if (identities === undefined) {
      return [];
    }
    return identities.map((identity) => {
      return { id: identity.id, firstName: identity.firstName, lastName: identity.lastName, email: identity.email };
    });
  };

  return (
    <div className={styles.container}>
      <h2 className={styles.title}>Identities</h2>
      <div className={styles.actionContainer}>
        <TextInput
          id='identity'
          name='identity'
          label='Search'
          placeholder='Enter name or id'
          value={identitySearchValue}
          onChange={(event) => onIdentitySearchChange(event)}
          onKeyDown={(event) => onIdentitySearchKeyDown(event)}
        />
        <button className={styles.addIdentity} type='button'>
          Add identity
        </button>
      </div>
      <div className={styles.resultsTableContainer}>
        <ResultsTable header={getResultsTableHeaderRow()} data={getResultsData()} />
      </div>
    </div>
  );
};

export default IdentitySearch;

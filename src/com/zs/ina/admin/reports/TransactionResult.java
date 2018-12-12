package com.zs.ina.admin.reports;

import java.util.Date;


/**
 * 
 */

/**
 * @author  Michael Grecol
 *	@project JasperViewerFx
 * @filename TransactionResult.java
 * @date Mar 23, 2015
 */
public class TransactionResult {
	private long resultNumber;
	private String resultDescription;
	private String result;
	private Date transactionTime;
	private int objectId;

    /**
     *
     * @return
     */
    public long getResultNumber() {
		return resultNumber;
	}

    /**
     *
     * @param resultNumber
     */
    public void setResultNumber(long resultNumber) {
		this.resultNumber = resultNumber;
	}

    /**
     *
     * @return
     */
    public String getResultDescription() {
		return resultDescription;
	}

    /**
     *
     * @param resultDescription
     */
    public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

    /**
     *
     * @return
     */
    public String getResult() {
		return result;
	}

    /**
     *
     * @param result
     */
    public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TransactionResult [resultNumber=" + resultNumber
				+ ", resultDescription=" + resultDescription + ", result=" + result
				+ ", transactionTime=" + transactionTime+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (resultNumber ^ (resultNumber >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionResult other = (TransactionResult) obj;
		if (resultNumber != other.resultNumber)
			return false;
		return true;
	}

    /**
     *
     */
    public TransactionResult(){
		
	}

    /**
     *
     * @param resultNumber
     * @param resultDescription
     * @param result
     * @param transactionTime
     */
    public TransactionResult(long resultNumber, String resultDescription,
			String result, Date transactionTime) {
		super();
		this.resultNumber = resultNumber;
		this.resultDescription = resultDescription;
		this.result = result;
		this.transactionTime = transactionTime;
	}

    /**
     *
     * @return
     */
    public Date getTransactionTime() {
		return transactionTime;
	}

    /**
     *
     * @param transactionTime
     */
    public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

    /**
     *
     * @return
     */
    public int getObjectId() {
		return objectId;
	}

    /**
     *
     * @param objectId
     */
    public void setObjectId(int objectId) {
		this.objectId = objectId;
	}


}

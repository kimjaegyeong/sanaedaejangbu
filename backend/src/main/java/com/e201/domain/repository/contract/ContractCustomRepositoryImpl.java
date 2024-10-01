package com.e201.domain.repository.contract;

import static com.e201.domain.entity.contract.ContractStatus.*;
import static com.e201.domain.entity.contract.QContract.*;

import java.time.LocalDateTime;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.HashMap;
=======
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
import java.util.HashMap;
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.e201.api.controller.contract.response.ContractFindResponse;
import com.e201.domain.entity.company.QCompany;
import com.e201.domain.entity.contract.Contract;
import com.e201.domain.entity.contract.ContractFindCond;
import com.e201.domain.entity.contract.ContractFindStatus;
import com.e201.domain.entity.contract.QContract;
import com.e201.domain.entity.store.QStore;
import com.e201.global.security.auth.constant.RoleType;
import com.e201.global.security.auth.dto.AuthInfo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

<<<<<<< HEAD
<<<<<<< HEAD
@Repository
public class ContractCustomRepositoryImpl implements ContractCustomRepository {
=======
import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Repository
public class ContractCustomRepositoryImpl implements ContractCustomRepository{
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
@Repository
public class ContractCustomRepositoryImpl implements ContractCustomRepository {
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)

	private final JPAQueryFactory contractQueryFactory;
	private final JPAQueryFactory companyQueryFactory;
	private final JPAQueryFactory storeQueryFactory;

	public ContractCustomRepositoryImpl(@Qualifier("contractJpaQueryFactory") JPAQueryFactory contractQueryFactory,
		@Qualifier("companyJpaQueryFactory") JPAQueryFactory companyQueryFactory,
		@Qualifier("storeJpaQueryFactory") JPAQueryFactory storeQueryFactory) {
		this.contractQueryFactory = contractQueryFactory;
		this.companyQueryFactory = companyQueryFactory;
		this.storeQueryFactory = storeQueryFactory;
	}

	@Override
<<<<<<< HEAD
<<<<<<< HEAD
	public List<ContractFindResponse> findMyContracts(AuthInfo authInfo, ContractFindStatus status,
		ContractFindCond cond, LocalDateTime lastContractDate, int pageSize) {
=======
	public List<ContractFindResponse> findMyContracts(AuthInfo authInfo, ContractFindStatus status, ContractFindCond cond, LocalDateTime lastContractDate, int pageSize) {
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
	public List<ContractFindResponse> findMyContracts(AuthInfo authInfo, ContractFindStatus status,
		ContractFindCond cond, LocalDateTime lastContractDate, int pageSize) {
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
		// 1. contractDB에서 계약 데이터 조회
		QContract contract = QContract.contract;
		List<Contract> contracts = contractQueryFactory
			.selectFrom(contract)
			.where(
				lastContractDate != null ? contract.createdAt.gt(lastContractDate) :
					null,
				eqStatus(authInfo, status, cond),
				eqId(authInfo),
				contract.deleteYN.eq("N"))
			.orderBy(contract.createdAt.desc())
			.limit(pageSize)
			.fetch();

<<<<<<< HEAD
<<<<<<< HEAD
		Map<UUID, Map<String, String>> companyMap = getCompanyMap(contracts);
		Map<UUID, Map<String, String>> storeMap = getStoreMap(contracts);
=======
		Map<UUID, String> companyMap = getCompanyMap(contracts);
		Map<UUID, String> storeMap = getStoreMap(contracts);
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
		Map<UUID, Map<String, String>> companyMap = getCompanyMap(contracts);
		Map<UUID, Map<String, String>> storeMap = getStoreMap(contracts);
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)

		// DTO로 변환
		List<ContractFindResponse> result = contracts.stream()
			.map(contractData -> new ContractFindResponse(
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
				String.valueOf(contractData.getId()),
				String.valueOf(contractData.getCompanyId()),
				companyMap.get(contractData.getCompanyId()).get("companyName"),
				companyMap.get(contractData.getCompanyId()).get("companyEmail"),
				companyMap.get(contractData.getCompanyId()).get("companyPhone"),
				companyMap.get(contractData.getCompanyId()).get("companyAddress"),
				String.valueOf(contractData.getStoreId()),
				storeMap.get(contractData.getStoreId()).get("storeName"),
				storeMap.get(contractData.getStoreId()).get("storeEmail"),
				storeMap.get(contractData.getStoreId()).get("storePhone"),
				storeMap.get(contractData.getStoreId()).get("storeAddress"),
<<<<<<< HEAD
				contractData.getCreatedAt(),
				contractData.getSettlementDay(),
				String.valueOf(contractData.getStatus())
=======
				contractData.getCreatedAt(),
				contractData.getSettlementDay(),
				String.valueOf(contractData.getId()),
				String.valueOf(contractData.getStoreId()),
				String.valueOf(contractData.getCompanyId()),
				storeMap.get(contractData.getStoreId()),
				companyMap.get(contractData.getCompanyId())
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
				contractData.getCreatedAt(),
				contractData.getSettlementDay(),
				String.valueOf(contractData.getStatus())
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
			))
			.collect(Collectors.toList());

		return result;
	}

<<<<<<< HEAD
<<<<<<< HEAD
	private BooleanExpression eqStatus(AuthInfo authInfo, ContractFindStatus status, ContractFindCond cond) {
		if (status == null)
			return null;

		return switch (status) {
=======
	private BooleanExpression eqStatus(AuthInfo authInfo, ContractFindStatus status, ContractFindCond cond){
		if(status == null) return null;

		return switch (status){
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
	private BooleanExpression eqStatus(AuthInfo authInfo, ContractFindStatus status, ContractFindCond cond) {
		if (status == null)
			return null;

		return switch (status) {
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
			case IN -> getProgressStatusExpression(authInfo, cond);
			case REJECT -> contract.status.eq(COMPANY_REJECT).or(contract.status.eq(STORE_REJECT));
			case CANCELED -> contract.status.eq(CANCEL);
			case COMPLETE -> contract.status.eq(COMPLETE);
			default -> null;
		};
	}

<<<<<<< HEAD
<<<<<<< HEAD
	private BooleanExpression getProgressStatusExpression(AuthInfo authInfo, ContractFindCond cond) {
		if (authInfo == null || cond == null)
			return null;

		BooleanExpression senderCondition = (authInfo.getRoleType() == RoleType.COMPANY) ?
			contract.status.eq(COMPANY_REQUEST) : contract.status.eq(STORE_REQUEST);

		BooleanExpression receiverCondition = (authInfo.getRoleType() == RoleType.COMPANY) ?
			contract.status.eq(STORE_REQUEST) : contract.status.eq(COMPANY_REQUEST);

		return switch (cond) {
=======
	private BooleanExpression getProgressStatusExpression(AuthInfo authInfo, ContractFindCond cond){
		if (authInfo == null || cond == null) return null;
=======
	private BooleanExpression getProgressStatusExpression(AuthInfo authInfo, ContractFindCond cond) {
		if (authInfo == null || cond == null)
			return null;
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)

		BooleanExpression senderCondition = (authInfo.getRoleType() == RoleType.COMPANY) ?
			contract.status.eq(COMPANY_REQUEST) : contract.status.eq(STORE_REQUEST);

		BooleanExpression receiverCondition = (authInfo.getRoleType() == RoleType.COMPANY) ?
			contract.status.eq(STORE_REQUEST) : contract.status.eq(COMPANY_REQUEST);

<<<<<<< HEAD
		return switch(cond){
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
		return switch (cond) {
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
			case SENDER -> senderCondition;
			case RECEIVER -> receiverCondition;
			default -> senderCondition.or(receiverCondition);
		};
	}

<<<<<<< HEAD
<<<<<<< HEAD
	private BooleanExpression eqId(AuthInfo authInfo) {
		return switch (authInfo.getRoleType()) {
=======
	private BooleanExpression eqId(AuthInfo authInfo){
		return switch (authInfo.getRoleType()){
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
	private BooleanExpression eqId(AuthInfo authInfo) {
		return switch (authInfo.getRoleType()) {
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
			case STORE -> contract.storeId.eq(authInfo.getId());
			case COMPANY -> contract.companyId.eq(authInfo.getId());
			default -> throw new IllegalStateException("Unexpected value: " + authInfo.getRoleType());
		};
	}

<<<<<<< HEAD
<<<<<<< HEAD
	private Map<UUID, Map<String, String>> getStoreMap(List<Contract> contracts) {
=======
	private Map<UUID, String> getStoreMap(List<Contract> contracts){
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
	private Map<UUID, Map<String, String>> getStoreMap(List<Contract> contracts) {
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
		Set<UUID> storeIds = contracts.stream()
			.map(Contract::getStoreId)
			.collect(Collectors.toSet());

		QStore store = QStore.store;
		return storeQueryFactory.select(store.id, store.storeInfo.name)
			.from(store)
			.where(store.id.in(storeIds),
				store.deleteYN.eq("N"))
			.fetch()
			.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(store.id),
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
				tuple -> {
					Map<String, String> storeInfo = new HashMap<>();
					storeInfo.put("storeName", tuple.get(store.storeInfo.name));
					storeInfo.put("storeEmail", tuple.get(store.email));
					storeInfo.put("storePhone", tuple.get(store.storeInfo.phone));
					storeInfo.put("storeAddress", tuple.get(store.storeInfo.businessAddress));
					return storeInfo;
				}
<<<<<<< HEAD
			));
	}

	private Map<UUID, Map<String, String>> getCompanyMap(List<Contract> contracts) {
=======
				tuple -> tuple.get(store.storeInfo.name)
			));
	}

	private Map<UUID, String> getCompanyMap(List<Contract> contracts){
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
			));
	}

	private Map<UUID, Map<String, String>> getCompanyMap(List<Contract> contracts) {
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
		Set<UUID> companyIds = contracts.stream()
			.map(Contract::getCompanyId)
			.collect(Collectors.toSet());

		QCompany company = QCompany.company;
		return companyQueryFactory
			.select(company.id, company.companyInfo.name)
			.from(company)
			.where(company.id.in(companyIds))
			.fetch()
			.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(company.id),
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
				tuple -> {
					Map<String, String> companyInfo = new HashMap<>();
					companyInfo.put("companyName", tuple.get(company.companyInfo.name));
					companyInfo.put("companyEmail", tuple.get(company.email));
					companyInfo.put("companyPhone", tuple.get(company.companyInfo.phone));
					companyInfo.put("companyAddress", tuple.get(company.companyInfo.businessAddress));
					return companyInfo;
				}
<<<<<<< HEAD
=======
				tuple -> tuple.get(company.companyInfo.name)
>>>>>>> 31cf432 ([#40] feat: Contract 조회 기능 구현)
=======
>>>>>>> 54ad0bd ([#40] feat: 계약 조회 기능 구현)
			));
	}
}
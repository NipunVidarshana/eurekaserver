package com.logic.springjwt.services;

import com.logic.springjwt.models.LogTransaction;
import com.logic.springjwt.repository.LogTransactionRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class TransactionLogServices {

    @Autowired
    private LogTransactionRepository dataRepository;

    public boolean TransactionLog(String tableName, String typeOfTask, String userName,String affectedId) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        LogTransaction transactionLog = new LogTransaction();
        transactionLog.setUserName(userName);
        transactionLog.setIpAddress(ip);
        transactionLog.setTableName(tableName);
        transactionLog.setTask(typeOfTask);
        transactionLog.setDateTime(date);
        transactionLog.setAffectedId(affectedId);

        try {
            dataRepository.save(transactionLog);
            Runtime.getRuntime().gc();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

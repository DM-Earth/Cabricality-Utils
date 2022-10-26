package com.dm.earth.cabricality.util;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;

@SuppressWarnings({"UnstableApiUsage", "deprecation"})
public class TransferUtil {
    public static Transaction getTransaction() {
        if (Transaction.isOpen()) {
            TransactionContext open = Transaction.getCurrentUnsafe();
            if (open != null) {
                return open.openNested();
            }
        }
        return Transaction.openOuter();
    }
}

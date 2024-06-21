package com.getext.room.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.getext.room.entity.RecognizedText;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RecognizedTextDao_Impl implements RecognizedTextDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RecognizedText> __insertionAdapterOfRecognizedText;

  private final EntityDeletionOrUpdateAdapter<RecognizedText> __deletionAdapterOfRecognizedText;

  private final EntityDeletionOrUpdateAdapter<RecognizedText> __updateAdapterOfRecognizedText;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllRecognizedTexts;

  public RecognizedTextDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecognizedText = new EntityInsertionAdapter<RecognizedText>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `recognized_text` (`id`,`mode`,`timestamp`,`text`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RecognizedText value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getRecognitionMode());
        stmt.bindLong(3, value.getTimestamp());
        if (value.getRecognizedText() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRecognizedText());
        }
      }
    };
    this.__deletionAdapterOfRecognizedText = new EntityDeletionOrUpdateAdapter<RecognizedText>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `recognized_text` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RecognizedText value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfRecognizedText = new EntityDeletionOrUpdateAdapter<RecognizedText>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `recognized_text` SET `id` = ?,`mode` = ?,`timestamp` = ?,`text` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RecognizedText value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getRecognitionMode());
        stmt.bindLong(3, value.getTimestamp());
        if (value.getRecognizedText() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRecognizedText());
        }
        stmt.bindLong(5, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllRecognizedTexts = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM recognized_text";
        return _query;
      }
    };
  }

  @Override
  public void insertRecognizedText(final RecognizedText recognizedText) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRecognizedText.insert(recognizedText);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteRecognizedText(final RecognizedText recognizedText) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfRecognizedText.handle(recognizedText);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateRecognizedText(final RecognizedText recognizedText) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfRecognizedText.handle(recognizedText);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllRecognizedTexts() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllRecognizedTexts.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllRecognizedTexts.release(_stmt);
    }
  }

  @Override
  public LiveData<List<RecognizedText>> getAllRecognizedTexts() {
    final String _sql = "SELECT * FROM recognized_text ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"recognized_text"}, false, new Callable<List<RecognizedText>>() {
      @Override
      public List<RecognizedText> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMRecognitionMode = CursorUtil.getColumnIndexOrThrow(_cursor, "mode");
          final int _cursorIndexOfMTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfMRecognizedText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final List<RecognizedText> _result = new ArrayList<RecognizedText>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final RecognizedText _item;
            _item = new RecognizedText();
            final int _tmpMId;
            _tmpMId = _cursor.getInt(_cursorIndexOfMId);
            _item.setId(_tmpMId);
            final int _tmpMRecognitionMode;
            _tmpMRecognitionMode = _cursor.getInt(_cursorIndexOfMRecognitionMode);
            _item.setRecognitionMode(_tmpMRecognitionMode);
            final long _tmpMTimestamp;
            _tmpMTimestamp = _cursor.getLong(_cursorIndexOfMTimestamp);
            _item.setTimestamp(_tmpMTimestamp);
            final String _tmpMRecognizedText;
            _tmpMRecognizedText = _cursor.getString(_cursorIndexOfMRecognizedText);
            _item.setRecognizedText(_tmpMRecognizedText);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}

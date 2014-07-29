package com.ford.syncV4.android.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.ford.syncV4.android.MainApp;
import com.ford.syncV4.android.constants.Const;
import com.ford.syncV4.protocol.heartbeat.HeartbeatMonitor;
import com.ford.syncV4.proxy.constants.ProtocolConstants;
import com.ford.syncV4.transport.TransportType;

/**
 * Created with Android Studio.
 * Author: Chernyshov Yuriy - Mobile Development
 * Date: 12/27/13
 * Time: 10:51 AM
 */
public class AppPreferencesManager {

    /**
     * Set Transport Type that application use.
     * @param transportType Transport Type that application use.
     */
    public static void setTransportType(TransportType transportType) {
        if (transportType == null) {
            throw new NullPointerException(AppPreferencesManager.class.getSimpleName() +
                    " set transport type can not be NULL");
        }
        int transportTypeIntValue = Const.Transport.KEY_USB;
        switch (transportType) {
            case TCP:
                transportTypeIntValue = Const.Transport.KEY_TCP;
                break;
            case BLUETOOTH:
                transportTypeIntValue = Const.Transport.KEY_BLUETOOTH;
                break;
        }
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Const.Transport.PREFS_KEY_TRANSPORT_TYPE, transportTypeIntValue);
        editor.apply();
    }

    /**
     * @return Transport Type that application use.
     */
    public static TransportType getTransportType() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        int transportType = sharedPreferences.getInt(Const.Transport.PREFS_KEY_TRANSPORT_TYPE,
                Const.Transport.PREFS_DEFAULT_TRANSPORT_TYPE);
        switch (transportType) {
            case Const.Transport.KEY_BLUETOOTH:
                return TransportType.BLUETOOTH;
            case Const.Transport.KEY_USB:
                return TransportType.USB;
            case Const.Transport.KEY_TCP:
                return TransportType.TCP;
        }
        // Return default transport type
        return TransportType.USB;
    }

    /**
     * Set a path to the Policy Table Update file
     *
     * @param filePath path to the local file
     */
    public static void setPolicyTableUpdateFilePath(String filePath) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Const.Policy.PREF_KEY_POLICY_UPDATE_FILE_PATH, filePath);
        editor.apply();
    }

    /**
     * Get a path to the Policy Table Update local file
     *
     * @return path to the file
     */
    public static String getPolicyTableUpdateFilePath() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getString(Const.Policy.PREF_KEY_POLICY_UPDATE_FILE_PATH, "");
    }

    /**
     * Set a value of the auto replay scenario in case of processing Policy Table Snapshot data
     *
     * @param value {@link java.lang.Boolean} true | false
     */
    public static void setPolicyTableUpdateAutoReplay(boolean value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Const.Policy.PREF_KEY_POLICY_UPDATE_AUTO_REPLAY, value);
        editor.apply();
    }

    /**
     * Return a value of the auto replay scenario in case of processing Policy Table Snapshot data
     *
     * @return {@link java.lang.Boolean} true | false
     */
    public static boolean getPolicyTableUpdateAutoReplay() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.Policy.PREF_KEY_POLICY_UPDATE_AUTO_REPLAY, true);
    }

    /**
     * Set <b>true</b> if application need to use HashId, <b>false</b> - otherwise
     *
     * @param value {@link java.lang.Boolean} true | false
     */
    public static void setUseHashId(boolean value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Const.HashId.PREF_KEY_USE_HASH_ID, value);
        editor.apply();
    }

    /**
     * Return <b>true</b> if application need to use HashId, <b>false</b> - otherwise
     *
     * @return {@link java.lang.Boolean} true | false
     */
    public static boolean getUseHashId() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.HashId.PREF_KEY_USE_HASH_ID, true);
    }

    /**
     * Set <b>true</b> if application need to use Custom HashId, <b>false</b> - otherwise
     *
     * @param value {@link java.lang.Boolean} true | false
     */
    public static void setUseCustomHashId(boolean value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Const.HashId.PREF_KEY_USE_CUSTOM_HASH_ID, value);
        editor.apply();
    }

    /**
     * Return <b>true</b> if application need to use Custom HashId, <b>false</b> - otherwise
     *
     * @return {@link java.lang.Boolean} true | false
     */
    public static boolean getUseCustomHashId() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.HashId.PREF_KEY_USE_CUSTOM_HASH_ID, false);
    }

    /**
     * Set custom {@link com.ford.syncV4.proxy.rpc.RegisterAppInterface#setHashID(String)} field
     * value for the {@link com.ford.syncV4.proxy.rpc.RegisterAppInterface}
     *
     * @param value {@link java.lang.String}
     */
    public static void setCustomHashId(String value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Const.HashId.PREF_KEY_CUSTOM_HASH_ID, value);
        editor.apply();
    }

    /**
     * @return custom {@link com.ford.syncV4.proxy.rpc.RegisterAppInterface#setHashID(String)}
     * field value for the {@link com.ford.syncV4.proxy.rpc.RegisterAppInterface}
     */
    public static String getCustomHashId() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getString(Const.HashId.PREF_KEY_CUSTOM_HASH_ID, "");
    }

    /**
     * Set last used {@link com.ford.syncV4.proxy.rpc.RegisterAppInterface#getHashID()} Set
     *
     * @param value {@link java.util.Set}
     */
    public static void setLastUsedHashIds(String value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Const.HashId.PREF_KEY_LAST_HASH_IDS, value);
        editor.apply();
    }

    /**
     * @return last used {@link com.ford.syncV4.proxy.rpc.RegisterAppInterface#getHashID()} Set
     */
    public static String getLastUsedHashIds() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getString(Const.HashId.PREF_KEY_LAST_HASH_IDS, "");
    }

    /**
     * @return Whether or not application use custom AppId, <b>true</b> | <b>false</b>
     */
    public static boolean getIsCustomAppId() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.PREF_KEY_IS_CUSTOM_APP_ID, false);
    }

    /**
     * Set whether or not application use custom AppId, <b>true</b> | <b>false</b>
     * @param value a boolean value
     */
    public static void setIsCustomAppId(boolean value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Const.PREF_KEY_IS_CUSTOM_APP_ID, value);
        editor.apply();
    }

    /**
     * Set custom App Id value
     *
     * @param value String value
     */
    public static void setCustomAppId(String value) {
        if (value == null) {
            throw new NullPointerException(AppPreferencesManager.class.getSimpleName() +
                    " set custom App Id can not be NULL");
        }
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Const.PREF_KEY_CUSTOM_APP_ID, value);
        editor.apply();
    }

    /**
     * @return custom App Id value
     */
    public static String getCustomAppId() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getString(Const.PREF_KEY_CUSTOM_APP_ID, "");
    }

    /**
     * Set flag that indicates whether to start Session as secured
     *
     * @param value boolean value
     */
    public static void setIsStartSecureSession(boolean value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Const.PREF_KEY_START_SECURE_SESSION, value);
        editor.apply();
    }

    /**
     * @return flag that indicates whether to start Session as secured
     */
    public static boolean getIsStartSecureSession() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.PREF_KEY_START_SECURE_SESSION, false);
    }

    /**
     * Returns the current state of the disable lock when testing flag.
     *
     * @return true if the screen lock is disabled
     */
    public static boolean getDisableLockFlag() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(
                Const.PREFS_KEY_DISABLE_LOCK_WHEN_TESTING,
                Const.PREFS_DEFAULT_DISABLE_LOCK_WHEN_TESTING);
    }

    /**
     * Toggles the current state of the disable lock when testing flag, and
     * writes it to the preferences.
     */
    public static void toggleDisableLock() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        boolean disableLock = sharedPreferences.getBoolean(
                Const.PREFS_KEY_DISABLE_LOCK_WHEN_TESTING,
                Const.PREFS_DEFAULT_DISABLE_LOCK_WHEN_TESTING);
        disableLock = !disableLock;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Const.PREFS_KEY_DISABLE_LOCK_WHEN_TESTING, disableLock);
        editor.apply();
    }

    /**
     * @return true if it is necessary to check whether device has been rooted
     */
    public static boolean getDoDeviceRootCheck() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.PREF_KEY_DO_DEVICE_ROOT_CHECK, true);
    }

    /**
     * Set protocol minimum supported version int value
     * @param value version of the protocol
     */
    public static void setProtocolMinVersion(int value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Const.PREFS_KEY_PROTOCOL_MIN_VERSION, value);
        editor.apply();
    }

    /**
     * Set true if it is necessary to check whether device has been rooted
     * @param value boolean value
     */
    public static void setDoDeviceRootCheck(boolean value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Const.PREF_KEY_DO_DEVICE_ROOT_CHECK, value);
        editor.apply();
    }

    /**
     * @return protocol minimum supported version int value
     */
    public static int getProtocolMinVersion() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getInt(Const.PREFS_KEY_PROTOCOL_MIN_VERSION, ProtocolConstants.PROTOCOL_VERSION_MIN);
    }

    /**
     * Set protocol maximum supported version int value
     * @param value version of the protocol
     */
    public static void setProtocolMaxVersion(int value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Const.PREFS_KEY_PROTOCOL_MAX_VERSION, value);
        editor.apply();
    }

    /**
     * @return protocol maximum supported version int value
     */
    public static int getProtocolMaxVersion() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getInt(Const.PREFS_KEY_PROTOCOL_MAX_VERSION, ProtocolConstants.PROTOCOL_VERSION_MIN);
    }

    /**
     * @return true if it is needed to set up application icon automatically
     */
    public static boolean getAutoSetAppIconFlag() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.PREFS_KEY_AUTOSETAPPICON,
                Const.PREFS_DEFAULT_AUTOSETAPPICON);
    }

    /**
     * @return the Context of the application
     */
    private static Context getAppContext() {
        return MainApp.getInstance().getApplicationContext();
    }

    /**
     * @return interval of the Heartbeat messages in milliseconds
     */
    public static int getHeartbeatInterval() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getInt(Const.PREF_KEY_HB_INTERVAL,
                HeartbeatMonitor.HEARTBEAT_INTERVAL);
    }

    /**
     * Set interval of the Heartbeat messages in milliseconds
     *
     * @param value value in milliseconds
     */
    public static void setHeartbeatInterval(int value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Const.PREF_KEY_HB_INTERVAL, value);
        editor.apply();
    }

    /**
     * @return true if it is necessary to send Ack for the Heartbeat Message
     * (which has been sent from SDL), false - otherwise
     */
    public static boolean getIsHeartbeatAck() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.PREF_KEY_HB_ACK, true);
    }

    /**
     * Set true if it is necessary to send Ack for the Heartbeat Message
     * (which has been sent from SDL), false - otherwise
     *
     * @param value true or false
     */
    public static void setIsHeartbeatAck(boolean value) {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Const.PREF_KEY_HB_ACK, value);
        editor.apply();
    }

    /**
     * @return true if it is necessary to reconnect when Heartbeat Timed Out, false - otherwise
     */
    public static boolean isReconnectOnHBTimeout() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.PREF_KEY_RECONNECT_ON_HB_TIMEOUT, true);
    }

    /**
     * @return true if it is necessary to process Ack from SDl on HeartBeat message, false - otherwise
     */
    public static boolean isProcessHeartBeatSDLAck() {
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences(Const.PREFS_NAME, 0);
        return sharedPreferences.getBoolean(Const.PREF_KEY_PROCESS_SDL_HB_ACK, true);
    }
}
package cat.Lacycat.ultimium.Feature.Curse;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public interface Curse extends Listener {
    /**
     * 이름을 반환합니다.
     * @return 이름
     */
    String getName();

    /**
     * 이름 색상을 반환합니다.
     * @return 이름 색상 (HEX)
     */
    String getNameColor();

    /**
     * 우선순위를 반환합니다.
     * @return 우선순위
     */
    int getPriority();

    /**
     * 현재 저주가 강도 수치를 사용하는지 확인합니다.
     * @return 강도 사용 여부
     */
    boolean usesIntensity();

    /**
     * 현재 저주에 대한 강도를 반환합니다.
     * @return 강도
     */
    int getIntensity();

    /**
     * 현재 저주의 강도를 n만큼 더합니다.
     * @param n 강도에 더할 숫자
     */
    void addIntensity(int n);

    /**
     * 현재 모드가 활성화중에 있는지 반환합니다.
     * @return 활성화 여부
     */
    boolean getEnabled();

    /**
     * 현재 모드 활성화 여부를 n으로 설정합니다.
     * @param n 덮어씌울 활성화 여부
     */
    void setEnabled(boolean n);

    /**
     * 현재 리스너를 등록합니다.
     * *가장 중요*
     * @param plugin 메인 플러그인 클래스의 인스턴스
     */
    void register(JavaPlugin plugin);
}

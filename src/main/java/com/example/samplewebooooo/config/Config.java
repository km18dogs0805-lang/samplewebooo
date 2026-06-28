package com.example.samplewebooooo.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Global configuration: HTTP headers, mirror lists, Cloudflare overrides, UI prefs.
 * Mirrors Python's config.py
 */
public class Config {

    // ── HTTP User-Agent ───────────────────────────────────────────────────────
    public static final Map<String, String> DEFAULT_HEADERS = Map.of(
        "User-Agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
        + "(KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36"
    );

    // ── Mirror lists per site key ─────────────────────────────────────────────
    public static final Map<String, List<String>> MIRRORS = Map.of(
        "missav", List.of("missav.ai", "missav.ws", "missav123.com", "missav.live"),
        "jable",  List.of("jable.tv", "fs1.app"),
        "supjav", List.of("supjav.com")
    );

    // // ── Cloudflare override store ─────────────────────────────────────────────
    // private static final ReentrantLock cfLock    = new ReentrantLock();
    // private static final ReentrantLock prefsLock = new ReentrantLock();

    // /** host → {cookie?, ua?} */
    // private static volatile Map<String, Map<String, String>> CF_OVERRIDES = new HashMap<>();

    // private static final ObjectMapper JSON = new ObjectMapper();

    // // ── Storage paths ─────────────────────────────────────────────────────────
    // public static Path cfStorePath() {
    //     String base = System.getenv("APPDATA");
    //     if (base == null || base.isBlank()) base = System.getProperty("user.home");
    //     return Path.of(base, "JableTV Downloader", "cf_overrides.json");
    // }

    // public static Path uiPrefsPath() {
    //     return cfStorePath().resolveSibling("ui_prefs.json");
    // }

    // public static Path queueCsvPath() {
    //     return uiPrefsPath().resolveSibling("download_queue.csv");
    // }

    // // ── Theme & language ──────────────────────────────────────────────────────
    // @SuppressWarnings("unchecked")
    // private static Map<String, Object> loadPrefs() {
    //     try {
    //         return JSON.readValue(uiPrefsPath().toFile(), Map.class);
    //     } catch (Exception e) {
    //         return new HashMap<>();
    //     }
    // }

    // private static void savePrefs(Map<String, Object> prefs) {
    //     Path p = uiPrefsPath();
    //     try {
    //         Files.createDirectories(p.getParent());
    //         Path tmp = p.resolveSibling(p.getFileName() + ".tmp");
    //         JSON.writerWithDefaultPrettyPrinter().writeValue(tmp.toFile(), prefs);
    //         Files.move(tmp, p, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    //     } catch (Exception ignored) {}
    // }

    // public static String getTheme() {
    //     Object v = loadPrefs().get("theme");
    //     if (v instanceof String s) {
    //         s = s.strip().toLowerCase();
    //         if (Set.of("system", "light", "dark").contains(s)) return s;
    //     }
    //     return "system";
    // }

    // public static void setTheme(String mode) {
    //     mode = (mode == null ? "" : mode).strip().toLowerCase();
    //     if (!Set.of("system", "light", "dark").contains(mode)) mode = "system";
    //     prefsLock.lock();
    //     try {
    //         Map<String, Object> prefs = new HashMap<>(loadPrefs());
    //         prefs.put("theme", mode);
    //         savePrefs(prefs);
    //     } finally { prefsLock.unlock(); }
    // }

    // public static String getUiLang() {
    //     Object v = loadPrefs().get("lang");
    //     if (v instanceof String s) {
    //         s = s.strip();
    //         if (Set.of("en", "zh", "zh-Hans", "ja").contains(s)) return s;
    //     }
    //     return null;
    // }

    // public static void setUiLang(String code) {
    //     code = (code == null ? "" : code).strip();
    //     if (!Set.of("en", "zh", "zh-Hans", "ja").contains(code)) code = "en";
    //     prefsLock.lock();
    //     try {
    //         Map<String, Object> prefs = new HashMap<>(loadPrefs());
    //         prefs.put("lang", code);
    //         savePrefs(prefs);
    //     } finally { prefsLock.unlock(); }
    // }

    // // ── Cloudflare overrides ──────────────────────────────────────────────────
    // private static String normHost(String host) {
    //     if (host == null) return "";
    //     host = host.strip().toLowerCase().replaceAll("\\.$", "");
    //     if (host.contains(":")) host = host.split(":", 2)[0].replaceAll("\\.$", "");
    //     return host;
    // }

    // private static String parseCfClearance(String raw) {
    //     if (raw == null) return "";
    //     raw = raw.strip().replaceAll("[\\x00-\\x1f\\x7f]+", "");
    //     if (raw.contains("cf_clearance=")) {
    //         var m = java.util.regex.Pattern.compile("cf_clearance=([^;,\\s]+)").matcher(raw);
    //         return m.find() ? m.group(1) : "";
    //     }
    //     return raw.replaceAll("^['\"]|['\"]$", "");
    // }

    // public static Map<String, String> getCfOverride(String host) {
    //     cfLock.lock();
    //     try {
    //         var entry = CF_OVERRIDES.get(normHost(host));
    //         return entry != null ? new HashMap<>(entry) : null;
    //     } finally { cfLock.unlock(); }
    // }

    // public static List<String> cfOverrideHosts() {
    //     cfLock.lock();
    //     try {
    //         List<String> list = new ArrayList<>(CF_OVERRIDES.keySet());
    //         Collections.sort(list);
    //         return list;
    //     } finally { cfLock.unlock(); }
    // }

    // public static void setCfOverride(String host, String cookie, String ua) {
    //     String h = normHost(host);
    //     if (h.isBlank()) return;
    //     Map<String, String> entry = new HashMap<>();
    //     String ck = parseCfClearance(cookie);
    //     if (!ck.isBlank()) entry.put("cookie", ck);
    //     if (ua != null && !ua.isBlank()) entry.put("ua", ua.strip());

    //     cfLock.lock();
    //     try {
    //         Map<String, Map<String, String>> next = new HashMap<>(CF_OVERRIDES);
    //         if (!entry.isEmpty()) next.put(h, entry);
    //         else next.remove(h);
    //         CF_OVERRIDES = next;
    //     } finally { cfLock.unlock(); }
    //     saveCfOverrides();
    // }

    // public static void clearCfOverride(String host) {
    //     setCfOverride(host, "", "");
    // }

    // @SuppressWarnings("unchecked")
    // public static void loadCfOverrides() {
    //     Path p = cfStorePath();
    //     if (!Files.exists(p)) {
    //         cfLock.lock(); try { CF_OVERRIDES = new HashMap<>(); } finally { cfLock.unlock(); }
    //         return;
    //     }
    //     try {
    //         Map<String, Object> raw = JSON.readValue(p.toFile(), Map.class);
    //         Map<String, Map<String, String>> parsed = new HashMap<>();
    //         for (var e : raw.entrySet()) {
    //             String h = normHost(e.getKey());
    //             if (h.isBlank() || !(e.getValue() instanceof Map<?,?> m)) continue;
    //             Map<String, String> clean = new HashMap<>();
    //             Object ckObj = m.get("cookie");
    //             if (ckObj instanceof String ck) {
    //                 ck = parseCfClearance(ck);
    //                 if (!ck.isBlank()) clean.put("cookie", ck);
    //             }
    //             Object uaObj = m.get("ua");
    //             if (uaObj instanceof String ua && !ua.isBlank()) clean.put("ua", ua.strip());
    //             if (!clean.isEmpty()) parsed.put(h, clean);
    //         }
    //         cfLock.lock(); try { CF_OVERRIDES = parsed; } finally { cfLock.unlock(); }
    //     } catch (Exception ex) {
    //         cfLock.lock(); try { CF_OVERRIDES = new HashMap<>(); } finally { cfLock.unlock(); }
    //     }
    // }

    // public static void saveCfOverrides() {
    //     cfLock.lock();
    //     Map<String, Map<String, String>> snapshot;
    //     try { snapshot = new HashMap<>(CF_OVERRIDES); } finally { cfLock.unlock(); }
    //     Path p = cfStorePath();
    //     try {
    //         Files.createDirectories(p.getParent());
    //         Path tmp = p.resolveSibling(p.getFileName() + ".tmp");
    //         JSON.writerWithDefaultPrettyPrinter().writeValue(tmp.toFile(), snapshot);
    //         Files.move(tmp, p, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    //     } catch (Exception ignored) {}
    //}

    //static { try { loadCfOverrides(); } catch (Exception ignored) {} }

}


    /**
    <iframe src="https://www.pornhub.com/embed/6a1f89f3e4709" frameborder="0" width="560" height="340" scrolling="no" allowfullscreen></iframe>
    
    https://jp.pornhub.com/view_video.php?viewkey=6a1f89f3e4709

    ==============================================
    plyr__control plyr__control--overlaid
    document.getElementById('video')
    [14]
    jpegがパラパラ漫画のように追加されていく
    file:33868d30-812f-45bb-94e9-9a094c6a64f3/seek
    ===============================================

======================================

        window.isPublished = true

        window.hash = window.location.hash.slice(1)

        if (window.hash.includes('internal')) {
            window.scenario = window.hash
            window.currentRecommendId = null
        } else if (window.hash.includes('_') && (window.hash.split('_')[0].length === 32 || window.hash.split('_')[0].length === 36)) {
            window.scenario = window.hash.split('_')[1]
            window.currentRecommendId = window.hash.split('_')[0]
        } else if (window.hash && (window.hash.length === 32 || window.hash.length === 36)) {
            window.scenario = null
            window.currentRecommendId = window.hash
        } else {
            window.scenario = null
            window.currentRecommendId = null
        }

        if (window.hash && ! window.hash.includes(':')) {
            window.history.replaceState(null, null, ' ')
        }

        window.dataLayer.push({
            event: 'videoVisit',
            item: {
                dvd_id: 'ofes-044',
            },
        })

        if (window.scenario) {
            window.dataLayer.push({
                event: 'recommendationVisit',
                recommendation: {
                    scenario: window.scenario,
                },
            })
        }

        document.addEventListener('DOMContentLoaded', () => {
            let source
            let isPreviewing = false

                            eval(function(p,a,c,k,e,d){e=function(c){return c.toString(36)};if(!''.replace(/^/,String)){while(c--){d[c.toString(a)]=k[c]||c.toString(a)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('f=\'8://7.6/5-4-3-2-1/e.0\';d=\'8://7.6/5-4-3-2-1/c/9.0\';b=\'8://7.6/5-4-3-2-1/a/9.0\';',16,16,'m3u8|9a094c6a64f3|94e9|45bb|812f|33868d30|com|surrit|https|video|1080p|source1280|720p|source842|playlist|source'.split('|'),0,{}))
            
            const video = document.querySelector('video.player')

            const initialPlayerEvent = () => {
                setTimeout(() => {
                    window.player.speed = 2

                    setTimeout(() => {
                        window.player.speed = 1
                    }, 50)
                }, 50)

                window.player.on('play', () => {
                    if (! hasPlayed) {
                        if (window.hls) {
                            window.hls.startLoad(-1)
                        }

                        hasPlayed = true

                        window.dataLayer.push({
                            event: 'videoPlay',
                            item: {
                                dvd_id: 'ofes-044',
                            },
                        })
                    }
                })

                window.player.on('enterfullscreen', () => {
                    screen.orientation.lock('landscape').catch(() => {})

                    setHlsDefaultLevel()
                })

                window.player.on('exitfullscreen', () => {
                    screen.orientation.lock('portrait').catch(() => {})

                    setHlsDefaultLevel()
                })

                let converted = false

                window.player.on('progress', (event) => {
                    if (! window.isPublished || converted || ! window.user_uuid) {
                        return
                    }

                    if (event.timeStamp > 100000) {
                        converted = true

                        window.recombeeClient.send(new recombee.AddPurchase(window.user_uuid, 'ofes-044', {
                            cascadeCreate: false,
                            recommId: window.currentRecommendId,
                        }))
                    }
                })

                if (! window.hls) {
                    let resetPlayerCallback = null

                    window.player.on('stalled', () => {
                        let source = window.player.source
                        let oldCurrentTime = 0
                        let newCurrentTime = 0

                        if (window.player.playing) {
                            oldCurrentTime = window.player.currentTime

                            if (resetPlayerCallback) {
                                clearTimeout(resetPlayerCallback)
                            }

                            resetPlayerCallback = setTimeout(oldCurrentTime => {
                                newCurrentTime = window.player.currentTime

                                if (oldCurrentTime === newCurrentTime) {
                                    let presevedTime = window.player.currentTime

                                    window.player.once('canplay', () => {
                                        window.player.currentTime = presevedTime
                                    })

                                    video.src = ''
                                    video.src = source

                                    window.player.play()
                                }
                            }, 500, oldCurrentTime)
                        }
                    })
                }

                document.querySelector('[data-plyr=mute]').addEventListener('click', () => {
                    if (! window.player.muted && window.player.volume === 0) {
                        window.player.volume = 100
                    }
                })
            }

            const setHlsDefaultLevel = () => {
                if (! window.hls) {
                    return
                }

                window.hls.currentLevel = window.hls.levels.findIndex((level, levelIndex) =>
                    level.width + 20 > window.innerWidth || levelIndex === window.hls.levels.length - 1
                )
            }

            let hasPlayed = false

            let playerSettings = {
                controls: [
                    'play-large',
                    'rewind',
                    'play',
                    'fast-forward',
                    'progress',
                    'current-time',
                    'duration',
                    'mute',
                    'captions',
                    'settings',
                    'pip',
                    'fullscreen',
                    'volume',
                ],
                fullscreen: {
                    enabled: true,
                    fallback: true,
                    iosNative: true,
                    container: null,
                },
                speed: {
                    selected: 1,
                    options: [0.25, 0.5, 1, 1.25, 1.5, 2],
                },
                i18n: {
                    speed: 'スピード',
                    normal: '普通',
                    quality: '品質',
                    qualityLabel: {
                        0: '自動',
                    },
                },
                thumbnail: {
                                            enabled: true,
                        pic_num: 4256,
                        width: 300,
                        height: 168,
                        col: 6,
                        row: 6,
                        offsetX: 0,
                        offsetY: 0,
                        urls: ["https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_0.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_1.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_2.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_3.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_4.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_5.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_6.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_7.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_8.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_9.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_10.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_11.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_12.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_13.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_14.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_15.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_16.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_17.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_18.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_19.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_20.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_21.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_22.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_23.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_24.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_25.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_26.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_27.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_28.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_29.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_30.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_31.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_32.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_33.jpg","https:\/\/nineyu.com\/33868d30-812f-45bb-94e9-9a094c6a64f3\/seek\/_34.jpg","https:\/\/n…

===================================

    =============================================
 const closeButton = document.createElement('button')

    closeButton.className = 'absolute top-1 right-1 p-0.5 bg-black rounded-lg opacity-70'
    closeButton.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 text-white"><path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" /></svg>'
    closeButton.addEventListener('click', () => {
        div.remove()
    })

    const div = document.createElement('div')

    div.className = 'fixed right-2 bottom-2'

    div.append(a)
    div.append(closeButton)

    document.body.append(div)
})
                    htmlAdIndexes.push(1, 1, 1)
                                    htmlAds.push(() => {
    const closeButton = document.createElement('button')

    closeButton.className = 'absolute top-1 right-1 p-0.5 bg-black rounded-lg opacity-70'
    closeButton.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 text-white"><path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" /></svg>'
    closeButton.addEventListener('click', () => {
        div.remove()
    })

    const div = document.createElement('div')

    div.className = 'fixed right-2 bottom-2'

    div.insertAdjacentHTML('afterbegin', `
        <div class="mx-auto" style="width: 300px; height: 100px;">
            <iframe referrerpolicy="no-referrer" src="https://t.rallytrck.website/s1/761080d1-3f0c-4cd7-98d8-763a4fcf5ca4?cv1={impressionId}&cv2={userId}&cv3={device}&cv4={creativeId}&cv5={campaignId}&cv6={language}&cv8={browser}&cv9={siteId}&cv10={creativeName}" width="300" height="100" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"></iframe>
        </div>
    `)

    div.append(closeButton)

    document.body.append(div)
})
    _________________________________________
    window.player.on('play', () => {
                    if (! hasPlayed) {
                        if (window.hls) {
                            window.hls.startLoad(-1)
                        }

                        hasPlayed = true

                        window.dataLayer.push({
                            event: 'videoPlay',
                            item: {
                                dvd_id: 'ofes-044',
                            },
                        })
                    }
    })

    MissAV 初回アクセスの時の状態、広告ページへ遷移するためのスクリプト
    document.querySelector('[data-plyr=mute]').addEventListener('click', () => {
                    if (! window.player.muted && window.player.volume === 0) {
                        window.player.volume = 100
                    }
                })

    */

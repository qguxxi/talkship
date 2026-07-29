# Talkship Product Requirements Document

> Production product requirements for the Talkship Android application.

| Field | Value |
|---|---|
| Product | Talkship |
| Platform | Android first |
| Document status | Draft |
| Version | 1.0 |
| Product stage | Pre-production |
| Primary market | To be validated |
| Owner | Product |
| Last updated | 2026-07-30 |

## 1. Document Purpose

This Product Requirements Document defines the production requirements for
Talkship, an Android-first conversational translation application.

It is the shared product contract for design, Android engineering, backend
engineering, quality assurance, security, analytics, operations, and release
management. It describes what the product must accomplish and how success will
be measured. Technical implementation details are defined separately in
`ARCHITECTURE.md`.

Every production requirement uses a stable identifier so that it can be linked
to design files, engineering tasks, test cases, analytics events, and release
checklists.

## 2. Product Summary

Talkship helps people communicate across language barriers through fast,
natural, two-way spoken conversations.

A user selects two languages, starts a conversation, and speaks normally.
Talkship captures speech, displays live transcription, translates it, and can
play the translated result aloud. The completed conversation can be reviewed,
renamed, exported, or deleted according to the user's privacy preferences.

The initial product is Android-first and optimized for in-person conversations.
It should feel immediate, calm, and trustworthy, especially when a user needs to
communicate under time pressure.

## 3. Problem Statement

People who do not share a language often rely on slow text entry, repeatedly
passing a device back and forth, or translation tools that are difficult to use
during a live conversation.

Existing experiences commonly fail because:

- starting a conversation takes too many steps;
- users cannot tell whether the application is listening;
- partial and final translations are visually confusing;
- speaker turns and source languages are unclear;
- poor connectivity interrupts the entire interaction;
- audio playback is difficult to control;
- users do not understand how their voice and transcripts are stored;
- permission, microphone, and provider errors are not recoverable.

Talkship must reduce these points of friction without making accuracy, privacy,
or failure states invisible.

## 4. Product Vision

Talkship should become the fastest trustworthy way to hold a natural bilingual
conversation on an Android phone.

The long-term product may support additional modes such as meetings, remote
calls, document translation, and cross-device conversations. Those modes are
outside the first release unless explicitly added to this PRD.

## 5. Goals

### 5.1 Launch goals

- Let a first-time user start a translated conversation in under one minute.
- Make listening, processing, translating, speaking, paused, and error states
  immediately understandable.
- Preserve completed transcript data reliably according to user preferences.
- Recover gracefully from temporary network, audio, and provider failures.
- Give users clear control over microphone access, playback, history, export,
  and deletion.
- Meet production standards for accessibility, security, privacy,
  observability, performance, and release quality.

### 5.2 Product principles

1. **Conversation first**: the primary action is always easy to find.
2. **Visible system state**: users always know what Talkship is doing.
3. **Human control**: users can pause, correct, replay, copy, or stop.
4. **Privacy by default**: data use and retention are understandable and limited.
5. **Graceful degradation**: partial usefulness is better than an unexplained
   failure.
6. **Accuracy over false confidence**: uncertain results must not be presented
   as guaranteed.
7. **Inclusive interaction**: core functionality works with assistive
   technologies and varying levels of technical literacy.

## 6. Non-Goals for Version 1

The first production release does not include:

- replacing professional human interpreters in medical, legal, emergency, or
  other high-risk settings;
- emergency-service calling or dispatch;
- guaranteed interpretation accuracy;
- simultaneous translation of multi-person meetings;
- desktop, iOS, web, or wearable clients;
- video-call translation;
- document, image, or camera translation;
- public transcript sharing pages;
- a public developer API;
- human interpreter marketplace functionality;
- end-to-end encrypted cross-device messaging;
- offline translation for every supported language;
- organization administration or enterprise policy management.

These exclusions must be reflected in marketing and user-facing claims.

## 7. Target Users

### 7.1 Primary persona: Everyday traveler

Needs to communicate with hotel staff, drivers, restaurant employees, local
services, or other people while traveling.

Priorities:

- minimal setup;
- obvious controls;
- fast translation;
- useful performance on unstable networks;
- easy replay of translated speech.

### 7.2 Primary persona: Multilingual family member

Uses Talkship for recurring conversations with relatives or community members.

Priorities:

- preferred languages remembered;
- readable transcript;
- natural turn-taking;
- history and privacy controls;
- support for different speech speeds and accents.

### 7.3 Secondary persona: Frontline service worker

Uses Talkship during short, practical interactions with customers.

Priorities:

- fast session start;
- reliable repeated use;
- clear speaker orientation;
- easy phrase replay;
- predictable privacy behavior.

### 7.4 Accessibility persona

Uses screen readers, larger text, switch access, color correction, hearing
assistance, or reduced motion.

Priorities:

- non-color status cues;
- screen-reader labels and announcements;
- scalable text;
- large touch targets;
- transcript-first interaction when audio playback is unsuitable.

## 8. Assumptions and Dependencies

The initial PRD assumes:

- the first client is a native Android application;
- an internet connection is required for cloud-based speech recognition and
  translation unless an explicitly supported offline mode is available;
- a Talkship-controlled backend protects provider credentials and enforces
  authentication, quota, and policy;
- supported language pairs are determined by validated provider capability;
- text-to-speech quality and voice availability vary by language and device;
- microphone access is required for spoken conversation but not for viewing
  saved history;
- the exact account and monetization model will be confirmed before public
  launch.

The product must not imply offline availability, language support, or data
retention behavior that is not actually delivered.

## 9. Release Priority Definitions

| Priority | Definition |
|---|---|
| P0 | Required for a safe and usable production launch |
| P1 | Important for product quality or early retention; may follow launch if explicitly approved |
| P2 | Planned enhancement that is not required for the initial release |

Any deferred P0 requirement blocks production release unless Product,
Engineering, QA, and Security explicitly accept and document the risk.

## 10. Core User Journeys

### 10.1 First-run conversation

1. User opens Talkship.
2. User sees a short value explanation and privacy summary.
3. User chooses source and target languages.
4. User starts a conversation.
5. Talkship requests microphone permission in context.
6. User grants permission.
7. Talkship confirms that it is listening.
8. User speaks.
9. Partial transcription appears.
10. Final transcription and translation appear.
11. Translated speech plays when playback is enabled.
12. The other speaker responds.
13. User ends the conversation.
14. User chooses whether to save or discard the session based on configured
    retention behavior.

### 10.2 Returning conversation

1. User opens Talkship.
2. Previously selected languages are available.
3. User starts a conversation from the home screen.
4. User pauses, resumes, swaps languages, or replays a translation as needed.
5. User ends the session and returns to home or history.

### 10.3 Recoverable interruption

1. An active session loses connectivity or provider access.
2. Talkship preserves finalized local transcript segments.
3. The UI explains the interruption and current recording state.
4. Talkship retries safely or offers a retry action.
5. User resumes or ends the session without losing completed content.

### 10.4 History management

1. User opens history.
2. User finds a session by date, title, or supported search criteria.
3. User opens session details.
4. User copies, exports, renames, or deletes the session.
5. Destructive actions clearly state their scope and result.

## 11. Functional Requirements

### 11.1 Application shell and home

#### FR-HOME-001 — Home screen

**Priority:** P0

The application must provide a home screen containing:

- the primary action to start a conversation;
- current source and target languages;
- access to language selection;
- access to history and settings;
- visible account, quota, or connectivity status only when relevant.

**Acceptance criteria:**

- The primary conversation action is visible without scrolling on supported
  phone sizes.
- The primary action has an accessible label and a minimum 48 dp touch target.
- Loading account or history metadata does not block starting a conversation
  when the user is otherwise eligible.

#### FR-HOME-002 — Restore user choices

**Priority:** P0

Talkship must restore the user's last valid language pair and relevant playback
preferences after application restart.

**Acceptance criteria:**

- Invalid or no-longer-supported selections fall back to safe defaults.
- The fallback is communicated before recording begins.
- Restoring preferences does not restore an active microphone session.

#### FR-HOME-003 — Recent conversations

**Priority:** P1

The home screen should show a limited list of recent saved conversations with a
clear route to full history.

### 11.2 Onboarding and disclosure

#### FR-ONB-001 — First-run onboarding

**Priority:** P0

The first-run experience must explain:

- the product's core benefit;
- that spoken audio may be processed by remote services;
- that automated translation may contain errors;
- how to choose languages;
- why microphone permission is needed.

**Acceptance criteria:**

- The user can complete or skip non-essential educational screens.
- Required legal consent cannot be skipped where applicable.
- Onboarding completion is retained across application restarts.
- A user can revisit privacy and product guidance from settings.

#### FR-ONB-002 — Permission request timing

**Priority:** P0

Microphone permission must be requested only when the user initiates a feature
that needs recording.

**Acceptance criteria:**

- A rationale is shown before or with the platform prompt when appropriate.
- Denial does not prevent access to non-recording parts of the application.
- Repeated denial does not create a prompt loop.
- Permanent denial provides a route to Android application settings.

### 11.3 Language selection

#### FR-LANG-001 — Select conversation languages

**Priority:** P0

Users must be able to select a source and target language from the currently
supported language catalog.

**Acceptance criteria:**

- Each language is shown using a localized display name.
- Search is available when the catalog size makes scrolling impractical.
- Unsupported pairs cannot start a session.
- The UI explains why a pair is unavailable.

#### FR-LANG-002 — Automatic source-language detection

**Priority:** P1

Talkship should support automatic source-language detection where provider
quality is validated.

**Acceptance criteria:**

- Automatic detection is labeled clearly.
- The detected language is visible during the conversation.
- The user can override an incorrect detection.
- Auto-detection is disabled for unsupported combinations.

#### FR-LANG-003 — Swap languages

**Priority:** P0

Users must be able to swap source and target languages before or during a
conversation when the resulting pair is supported.

**Acceptance criteria:**

- Swapping does not delete finalized transcript segments.
- The active speaker/language state updates visibly.
- Any required streaming restart is communicated and completes safely.

#### FR-LANG-004 — Language capability metadata

**Priority:** P0

The product must distinguish capabilities by language, including:

- speech recognition;
- translation;
- text-to-speech;
- automatic detection;
- offline support if present.

The UI must not offer a capability that is unavailable for the selected
language.

### 11.4 Conversation lifecycle

#### FR-CONV-001 — Start conversation

**Priority:** P0

An eligible user must be able to start a conversation from the home screen.

**Preconditions:**

- valid language pair;
- microphone permission;
- available recording device;
- valid authentication or anonymous entitlement;
- available quota where quota applies;
- required network connectivity for the selected mode.

**Acceptance criteria:**

- Start is idempotent; repeated taps do not create duplicate sessions.
- The UI shows an initializing state immediately.
- The recording indicator appears only after recording actually starts.
- Initialization failure returns the UI to a recoverable state.

#### FR-CONV-002 — Visible session status

**Priority:** P0

The conversation screen must visibly distinguish:

- initializing;
- listening;
- speech detected;
- processing;
- translating;
- playing translated audio;
- paused;
- reconnecting;
- stopping;
- ended;
- failed.

**Acceptance criteria:**

- Status is not communicated by color alone.
- Important state changes have accessible announcements.
- The displayed state matches actual microphone and playback behavior.

#### FR-CONV-003 — Pause and resume

**Priority:** P0

Users must be able to pause and resume an active conversation.

**Acceptance criteria:**

- Pausing stops new microphone capture within the defined performance target.
- Finalized transcript content remains visible.
- Resuming does not create a duplicate conversation.
- The UI clearly indicates that Talkship is not listening while paused.

#### FR-CONV-004 — End conversation

**Priority:** P0

Users must be able to end an active conversation at any time.

**Acceptance criteria:**

- Recording stops even if final network processing fails.
- In-flight finalized content is retained where technically possible.
- The action cannot accidentally create multiple saved sessions.
- The user sees whether the session was saved, discarded, or requires recovery.

#### FR-CONV-005 — Background and interruption behavior

**Priority:** P0

Talkship must define and enforce behavior when:

- the app moves to the background;
- the device is locked;
- an incoming call takes audio focus;
- a Bluetooth or wired device disconnects;
- another application uses the microphone;
- the operating system stops the process.

**Acceptance criteria:**

- Recording never continues invisibly.
- If background recording is supported, a compliant foreground notification and
  visible controls are present.
- If background recording is not supported, the session pauses or ends safely.
- Finalized transcript segments remain recoverable after process recreation.

#### FR-CONV-006 — Session duration and usage limits

**Priority:** P0

If duration, quota, or plan limits apply, Talkship must display them before they
interrupt an active session.

**Acceptance criteria:**

- The user receives a warning before a predictable limit.
- Reaching a limit stops capture safely.
- Existing transcript data is not hidden behind an upgrade action.
- Limit calculations are enforced by the backend when they affect cost or
  entitlement.

### 11.5 Speech recognition

#### FR-STT-001 — Partial and final transcription

**Priority:** P0

Talkship must display partial speech-recognition results quickly and visually
distinguish them from finalized transcript segments.

**Acceptance criteria:**

- Partial content can be replaced without creating duplicate final content.
- Final transcript segments have stable ordering and identifiers.
- Empty, low-confidence, or non-speech input does not create misleading content.
- Provider metadata is not shown as user transcript.

#### FR-STT-002 — Speaker and language attribution

**Priority:** P0

Each finalized transcript segment must identify its conversation side and source
language.

**Acceptance criteria:**

- Speaker presentation remains understandable when the phone is passed between
  participants.
- Unknown attribution is displayed as unknown rather than guessed with false
  certainty.

#### FR-STT-003 — Recognition uncertainty

**Priority:** P1

Where useful confidence information is available and validated, Talkship should
indicate uncertain transcription without overwhelming the conversation UI.

The product must not expose raw provider confidence values without a user-tested
interpretation.

### 11.6 Translation

#### FR-TRN-001 — Translate finalized speech

**Priority:** P0

Talkship must translate finalized recognized speech into the selected target
language.

**Acceptance criteria:**

- Translation is associated with the correct source segment.
- Segment ordering remains stable when responses arrive out of order.
- A failed translation does not remove the source transcript.
- The user can retry a failed segment when retry is safe.

#### FR-TRN-002 — Streaming translation

**Priority:** P1

Talkship should show provisional streaming translation when quality and
stability meet release targets.

**Acceptance criteria:**

- Provisional translation is visually distinct.
- It is replaced by a final translation without duplication.
- Rapid updates do not make the transcript unreadable or inaccessible.

#### FR-TRN-003 — Translation correction

**Priority:** P2

Users may edit a finalized source transcript and request a replacement
translation.

Corrections must remain distinguishable from the original automated result in
stored metadata where auditability is required.

#### FR-TRN-004 — High-risk use warning

**Priority:** P0

Talkship must communicate that automated translation can contain errors and
should not replace a qualified interpreter for critical medical, legal, safety,
or emergency decisions.

The warning must be discoverable without interrupting every ordinary
conversation.

### 11.7 Text-to-speech and playback

#### FR-TTS-001 — Automatic playback

**Priority:** P0

Users must be able to enable or disable automatic playback of finalized
translations.

**Acceptance criteria:**

- Playback uses the correct target language where a voice is available.
- Unavailable voices are communicated without blocking text translation.
- The preference is remembered.
- Automatic playback does not overlap uncontrollably with new speech capture.

#### FR-TTS-002 — Manual replay

**Priority:** P0

Users must be able to replay a finalized translated segment.

**Acceptance criteria:**

- The currently playing segment is visible.
- Replay can be stopped.
- Rapid repeated taps do not create overlapping playback.
- Audio focus and output route changes are handled safely.

#### FR-TTS-003 — Playback speed

**Priority:** P1

Users should be able to select from a small set of understandable playback
speeds.

### 11.8 Transcript interaction

#### FR-TXT-001 — Live transcript

**Priority:** P0

The active conversation must display source and translated text in chronological
order.

**Acceptance criteria:**

- The latest content remains discoverable without forcing scroll when the user
  is reviewing older content.
- Long conversations remain responsive.
- Text supports Android font scaling.
- Source and translated text are distinguishable without relying only on color.

#### FR-TXT-002 — Copy segment

**Priority:** P0

Users must be able to copy source or translated text from a finalized segment.

The product should indicate successful copy without exposing copied content in
analytics.

#### FR-TXT-003 — Share segment

**Priority:** P1

Users should be able to share selected finalized text through the Android share
sheet after an explicit action.

#### FR-TXT-004 — Delete segment

**Priority:** P2

Users may delete an individual transcript segment from a saved conversation.

Deletion behavior must state whether associated source audio or translations
are also deleted.

### 11.9 Conversation history

#### FR-HIST-001 — Save completed session

**Priority:** P0

Talkship must apply the user's configured retention choice when a session ends.

Supported policy at launch must be one of:

- save history by default with clear disclosure;
- ask after every session;
- do not save history by default.

Product and Privacy must choose and document the final default before launch.

#### FR-HIST-002 — History list

**Priority:** P0

Users must be able to view locally available saved conversations ordered by most
recent activity.

Each list item must show:

- date or relative time;
- language pair;
- title or useful preview;
- session duration when available.

#### FR-HIST-003 — Conversation details

**Priority:** P0

Users must be able to open a saved conversation and view its finalized source
and translated transcript.

#### FR-HIST-004 — Rename conversation

**Priority:** P1

Users should be able to assign a local display title to a saved conversation.

#### FR-HIST-005 — Search history

**Priority:** P1

Users should be able to search saved conversation titles and transcript text
when local privacy policy permits indexing.

#### FR-HIST-006 — Delete conversation

**Priority:** P0

Users must be able to permanently delete a saved conversation.

**Acceptance criteria:**

- The confirmation states what will be deleted.
- Deletion includes related local transcript records and queued synchronization
  data.
- Remote deletion is requested when remote copies exist.
- Pending remote deletion is visible or retried safely.
- Deleted content does not reappear after synchronization.

#### FR-HIST-007 — Delete all history

**Priority:** P0

Users must be able to delete all saved conversation history from settings.

The action requires explicit confirmation and reports whether any remote deletion
is still pending.

#### FR-HIST-008 — Export conversation

**Priority:** P1

Users should be able to export a saved transcript in at least one portable,
human-readable format.

**Acceptance criteria:**

- Export is initiated explicitly.
- The user chooses the destination through Android system UI.
- Exported content includes language and timestamp context.
- Sensitive temporary files are deleted after use.

### 11.10 Settings

#### FR-SET-001 — Language preferences

**Priority:** P0

Settings must provide default source and target language preferences.

#### FR-SET-002 — Audio preferences

**Priority:** P0

Settings must provide:

- automatic translated-speech playback;
- playback speed if supported;
- preferred output behavior where the platform permits it.

#### FR-SET-003 — Data and privacy controls

**Priority:** P0

Settings must provide:

- current transcript retention behavior;
- delete-all-history action;
- privacy policy access;
- third-party processing disclosure;
- account deletion when accounts are supported;
- analytics or personalization controls where legally or contractually required.

#### FR-SET-004 — Accessibility preferences

**Priority:** P1

Talkship should expose product-specific accessibility controls only when they
cannot be adequately inherited from Android settings, such as reduced animation
or transcript display density.

#### FR-SET-005 — About and diagnostics

**Priority:** P0

Settings must show:

- application version;
- legal documents;
- support route;
- open-source licenses;
- a privacy-safe diagnostic identifier when needed for support.

### 11.11 Account and entitlement

The final launch decision must select either anonymous-first usage,
account-required usage, or a hybrid model.

#### FR-ACC-001 — Account state

**Priority:** P0 if accounts are required; otherwise P1

Users must be able to understand whether they are signed in and which data is
associated with their account.

#### FR-ACC-002 — Authentication

**Priority:** P0 if accounts are required

Authentication must use secure, recoverable platform-appropriate methods and
must not require Talkship to handle raw third-party passwords.

#### FR-ACC-003 — Sign out

**Priority:** P0 if accounts are supported

Signing out must clearly explain what happens to local history, remote history,
and unsynchronized changes.

#### FR-ACC-004 — Delete account

**Priority:** P0 if accounts are supported

Users must be able to initiate account deletion in the app. The flow must explain
scope, consequences, legal retention exceptions, and completion status.

#### FR-ENT-001 — Usage and plan visibility

**Priority:** P0 if paid limits apply

The application must show applicable plan, quota, renewal, and limit information
before purchase and before predictable interruption.

#### FR-ENT-002 — Purchase and restore

**Priority:** P0 if subscriptions are sold in the app

Purchases must follow Google Play requirements, validate entitlement on the
backend, support restoration, and handle pending, canceled, refunded, and expired
states.

### 11.12 Notifications

#### FR-NOT-001 — Foreground-service notification

**Priority:** P0 when background capture is supported

An active background conversation must have a persistent Android notification
that identifies recording state and offers safe pause or stop controls.

#### FR-NOT-002 — Promotional notifications

**Priority:** P2

Promotional or engagement notifications require separate user value,
permission, frequency policy, and measurement. They are not part of the initial
core experience.

### 11.13 Support and feedback

#### FR-SUP-001 — Contact support

**Priority:** P0

Users must be able to access a support route from settings and relevant terminal
error states.

#### FR-SUP-002 — Report a translation issue

**Priority:** P1

Users should be able to report a poor result.

**Privacy requirements:**

- transcript or audio content is never attached silently;
- the user reviews content before submission;
- consent and retention are stated;
- reports can be submitted without recording raw audio unless raw audio is
  essential and separately approved.

## 12. Error and Recovery Requirements

### ERR-001 — Permission denied

**Priority:** P0

The product must explain why recording cannot start and provide the correct next
action without blocking history or settings.

### ERR-002 — No network

**Priority:** P0

Before starting a cloud-dependent mode, Talkship must communicate missing
connectivity. During a session, it must preserve finalized local data and expose
a retry or safe end action.

### ERR-003 — Provider unavailable

**Priority:** P0

The product must distinguish a temporary service failure from user error and
must not repeatedly charge quota for an internally retried operation.

### ERR-004 — Microphone unavailable

**Priority:** P0

Talkship must handle another application using the microphone, disconnected
input devices, initialization failure, and unsupported hardware.

### ERR-005 — Authentication expired

**Priority:** P0 when accounts are supported

The user must be able to re-authenticate without losing finalized local session
content.

### ERR-006 — Storage failure

**Priority:** P0

If a session cannot be persisted, Talkship must disclose the failure before
claiming that the session was saved.

### ERR-007 — Process death and restart

**Priority:** P0

After unexpected process termination, Talkship must:

- never imply that recording is still active when it is not;
- recover finalized persisted transcript content;
- mark interrupted sessions clearly;
- avoid duplicating the session during recovery.

### ERR-008 — Unknown failure

**Priority:** P0

Unexpected failures must produce a safe terminal state, a non-sensitive support
reference, and a recovery route where possible.

## 13. Non-Functional Requirements

### 13.1 Performance

Performance targets are measured at the 95th percentile unless otherwise stated.
Final targets must be validated on representative low- and mid-range Android
devices and realistic mobile networks.

| ID | Requirement | Launch target |
|---|---|---|
| NFR-PERF-001 | Warm app start to interactive home | <= 1.5 seconds |
| NFR-PERF-002 | Cold app start to interactive home | <= 3.0 seconds |
| NFR-PERF-003 | Start tap to visible initializing state | <= 100 ms |
| NFR-PERF-004 | Start tap to active listening indication, excluding permission interaction | <= 1.5 seconds |
| NFR-PERF-005 | Speech end to first useful finalized transcription under healthy conditions | <= 2.0 seconds |
| NFR-PERF-006 | Final source text to first useful translation under healthy conditions | <= 2.0 seconds |
| NFR-PERF-007 | Pause action to microphone capture stopped | <= 500 ms |
| NFR-PERF-008 | Transcript scrolling | No sustained visible jank in supported session lengths |
| NFR-PERF-009 | Application Not Responding rate | Below Google Play bad-behavior threshold with internal alerting below that threshold |

Provider and network latency must be measured separately from device processing
latency.

### 13.2 Reliability

| ID | Requirement |
|---|---|
| NFR-REL-001 | Crash-free user rate must meet or exceed 99.8% during staged production rollout |
| NFR-REL-002 | Crash-free session rate must meet or exceed 99.5% |
| NFR-REL-003 | Finalized locally acknowledged transcript segments must survive ordinary app restart |
| NFR-REL-004 | Duplicate session creation must be prevented through idempotent operations |
| NFR-REL-005 | Retry behavior must be bounded and cancelable |
| NFR-REL-006 | Service degradation must be visible through operational monitoring |

Availability targets for backend services must be specified in the backend
service-level objectives before launch.

### 13.3 Scalability

The backend and provider integration must support:

- horizontal scaling of stateless request handling;
- per-user and per-device rate limiting;
- cost and quota enforcement;
- idempotency for billable operations;
- provider capacity monitoring;
- safe degradation during traffic spikes;
- regional and data-residency requirements selected for target markets.

Capacity assumptions and launch traffic forecasts must be documented before
load testing.

### 13.4 Accessibility

| ID | Requirement |
|---|---|
| NFR-A11Y-001 | Core flows must be operable with TalkBack |
| NFR-A11Y-002 | Interactive targets must be at least 48 x 48 dp unless an approved exception exists |
| NFR-A11Y-003 | Text must support Android font scaling without loss of core functionality |
| NFR-A11Y-004 | Status and speaker identity must not rely on color alone |
| NFR-A11Y-005 | Text and meaningful controls must meet WCAG 2.2 AA contrast targets |
| NFR-A11Y-006 | Motion must respect system animation settings and avoid unnecessary vestibular triggers |
| NFR-A11Y-007 | Dynamic transcript updates must not overwhelm screen-reader focus |
| NFR-A11Y-008 | Meaningful icons require localized accessible names |

Accessibility acceptance testing must include automated checks and manual
assistive-technology testing of critical journeys.

### 13.5 Localization

| ID | Requirement |
|---|---|
| NFR-L10N-001 | All user-facing strings must use localizable resources |
| NFR-L10N-002 | Layouts must support text expansion and right-to-left presentation |
| NFR-L10N-003 | Dates, times, numbers, language names, and durations must use locale-aware formatting |
| NFR-L10N-004 | Translation-language availability is separate from application-interface localization |
| NFR-L10N-005 | Legal and privacy text must be available for each launched market as required |

The launch UI locales and conversation languages must be maintained as separate,
versioned product catalogs.

### 13.6 Device and platform support

| ID | Requirement |
|---|---|
| NFR-PLAT-001 | Supported Android API levels must match the production Gradle configuration |
| NFR-PLAT-002 | Core flows must be tested on low-, mid-, and high-tier devices |
| NFR-PLAT-003 | Portrait phone layouts are P0 |
| NFR-PLAT-004 | Landscape and large-screen layouts must remain functional; optimized layouts are P1 |
| NFR-PLAT-005 | Wired, speaker, earpiece, and common Bluetooth route changes must fail safely |
| NFR-PLAT-006 | Application behavior must comply with current Android background execution and permission rules |

## 14. Security Requirements

| ID | Requirement |
|---|---|
| SEC-001 | Provider and backend secrets must not be embedded in the APK |
| SEC-002 | All remote communication must use TLS |
| SEC-003 | Authentication tokens must use platform-appropriate protected storage |
| SEC-004 | Sensitive values must be redacted from application, network, analytics, and crash logs |
| SEC-005 | Backend endpoints must enforce authentication, authorization, rate limiting, and input validation |
| SEC-006 | Billable and destructive requests must support replay or idempotency protection where applicable |
| SEC-007 | Dependencies and source must be scanned for known vulnerabilities and committed secrets |
| SEC-008 | Production builds must disable developer-only diagnostics and verbose sensitive logging |
| SEC-009 | Exported Android components must use least privilege |
| SEC-010 | A threat model and security review must be completed before public launch |
| SEC-011 | Security incidents must have documented triage, containment, notification, and remediation procedures |
| SEC-012 | Account deletion and transcript deletion must be authorized server-side |

Any certificate-pinning decision requires a documented rotation and recovery
strategy. Pinning is not a substitute for normal TLS validation.

## 15. Privacy and Data Governance

### 15.1 Data categories

The production data inventory must identify whether Talkship processes:

- raw or buffered audio;
- partial and finalized source transcripts;
- translated text;
- language selections;
- session timestamps and duration;
- account identifiers;
- device and application diagnostics;
- subscription and entitlement metadata;
- support attachments;
- analytics events.

### 15.2 Privacy requirements

| ID | Requirement |
|---|---|
| PRIV-001 | Every collected data category must have a defined purpose, legal basis where required, owner, storage location, and retention period |
| PRIV-002 | Raw audio must not be retained by Talkship by default unless retention is necessary, disclosed, and approved |
| PRIV-003 | Third-party processing must be disclosed before first use |
| PRIV-004 | User transcript content must not be included in analytics events |
| PRIV-005 | User transcript or audio must not be attached to support reports without explicit review and consent |
| PRIV-006 | Users must be able to delete saved conversations |
| PRIV-007 | Account users must be able to request account and associated-data deletion |
| PRIV-008 | Data export must include only the requesting user's authorized data |
| PRIV-009 | Production and test data must be separated |
| PRIV-010 | Real user transcript or audio data must not be copied into development fixtures |
| PRIV-011 | Retention jobs and deletion completion must be monitored |
| PRIV-012 | Privacy disclosures must match actual provider and backend behavior |

### 15.3 Recording transparency

The interface must show a persistent, unambiguous recording indicator whenever
the microphone is active. Product behavior must comply with applicable consent
laws in launched markets. Talkship must not imply that it can determine whether
all participants have legally consented.

## 16. Analytics and Measurement

### 16.1 North-star outcome

The initial north-star candidate is:

> Weekly successful conversation sessions.

A successful conversation session is a session in which:

- recording starts successfully;
- at least one finalized source segment is produced;
- at least one translated segment is produced;
- the session is not immediately abandoned due to a technical failure.

The final definition and minimum duration must be validated before launch.

### 16.2 Product metrics

| Metric | Purpose |
|---|---|
| Onboarding completion rate | Measure first-run clarity |
| Permission grant rate after contextual request | Measure trust and rationale quality |
| Conversation start success rate | Measure core readiness |
| Time to first finalized transcript | Measure speech experience |
| Time to first translation | Measure perceived speed |
| Successful conversation rate | Measure core value delivery |
| Sessions per weekly active user | Measure repeat utility |
| Session completion versus technical abandonment | Measure reliability |
| Translation replay rate | Measure playback usefulness |
| History revisit rate | Measure retained value |
| Seven-day and thirty-day retention | Measure sustained value |
| Error rate by category | Prioritize reliability work |
| Estimated provider cost per successful session | Measure unit economics |

### 16.3 Event requirements

All analytics events must:

- use documented names and property schemas;
- exclude raw speech, transcripts, translations, personal names, and free-form
  user content;
- distinguish user actions from system outcomes;
- include app version and non-identifying environment metadata;
- define event owner and retention;
- be validated in staging before production release.

Minimum event families:

```text
onboarding_started
onboarding_completed
permission_result
conversation_start_requested
conversation_started
conversation_paused
conversation_resumed
conversation_ended
conversation_failed
speech_first_partial_received
speech_final_received
translation_received
translation_failed
translation_playback_started
history_opened
conversation_deleted
conversation_exported
paywall_viewed
purchase_result
```

Event properties must use identifiers and bounded enums, not transcript content.

## 17. Product Safety and Content Handling

Talkship is a communication utility and may reproduce speech that is offensive,
incorrect, unsafe, or sensitive.

Requirements:

- do not market automated translation as guaranteed accurate;
- preserve the speaker's intended meaning where provider policy permits;
- clearly distinguish Talkship-generated notices from translated user content;
- do not silently alter translated meaning for stylistic reasons;
- provide high-risk-use guidance;
- document provider content restrictions that may interrupt legitimate use;
- treat abuse prevention, fraud, and illegal-content obligations as backend and
  policy requirements;
- provide a reporting and escalation process for product harm.

Any filtering or safety transformation that can change translated meaning must
be reviewed by Product, Safety, Legal, and Localization.

## 18. Design and Interaction Requirements

The Talkship design system is the source of truth for visual implementation.

Production UI must:

- use semantic design tokens;
- maintain a clear primary conversation action;
- use Talkship components for repeated interaction patterns;
- display pressed, focused, selected, disabled, loading, error, and success
  states;
- use Remix Icon through the approved icon wrapper and sizing rules;
- remain readable in the product's dark visual theme;
- avoid decorative animation that delays conversation actions;
- support system insets, keyboard, font scale, and varying screen sizes;
- include empty, skeleton/loading, partial-data, offline, and terminal-error
  states in design deliverables.

Critical screens requiring reviewed designs:

- onboarding and disclosure;
- home;
- language selection;
- microphone permission rationale;
- active conversation in every system state;
- transcript segment interactions;
- session completion;
- history list and details;
- deletion confirmations;
- settings and privacy controls;
- authentication, quota, and purchase states when applicable;
- recoverable and terminal errors.

## 19. Operational Requirements

### OPS-001 — Service observability

**Priority:** P0

Operations must have dashboards for:

- API availability and latency;
- provider error and throttling rates;
- conversation start success;
- STT and translation latency;
- authentication and entitlement failure;
- application crash and ANR health;
- synchronization and deletion backlog;
- estimated usage cost.

### OPS-002 — Alerting

**Priority:** P0

Alert thresholds, severity, notification routes, and owners must be defined
before staged production rollout.

### OPS-003 — Incident response

**Priority:** P0

The team must maintain an incident process covering detection, severity,
ownership, communication, mitigation, rollback, and post-incident review.

### OPS-004 — Feature control

**Priority:** P0

Risky provider capabilities and incomplete features should be controllable
through server-side configuration or feature flags.

Flags must:

- have owners;
- have safe defaults;
- not bypass authorization;
- be removable after rollout;
- avoid creating an untestable number of combinations.

### OPS-005 — Provider degradation

**Priority:** P0

The product must have a documented response for provider outage, severe latency,
quota exhaustion, and quality regression.

A fallback provider is optional, but user-visible failure behavior is mandatory.

## 20. QA and Acceptance Strategy

### 20.1 Required test coverage

Production acceptance includes:

- unit tests for domain and state transitions;
- repository and persistence integration tests;
- database migration tests;
- Compose interaction and accessibility tests;
- device tests for permission, recording, playback, audio focus, and route change;
- contract tests for Talkship backend APIs;
- end-to-end tests for critical journeys;
- performance tests for startup and conversation latency;
- long-session, reconnect, low-storage, low-memory, and process-death tests;
- localization and right-to-left layout review;
- security and privacy verification.

### 20.2 Critical launch test journeys

1. First install to first successful translation.
2. Permission denial, rationale, and recovery.
3. Start, pause, resume, swap languages, and end.
4. Incoming call and audio-focus interruption.
5. Bluetooth disconnect during recording and playback.
6. Network loss and recovery during a session.
7. Provider failure after finalized source transcription.
8. Process death and recovery of finalized content.
9. Save, reopen, rename, export, and delete history.
10. Delete all history.
11. Sign in, token expiry, sign out, and account deletion if accounts exist.
12. Quota exhaustion and purchase restoration if monetization exists.
13. TalkBack and large-font completion of the core conversation flow.

### 20.3 Defect release policy

- Open critical security, privacy, data-loss, unauthorized-recording, purchase,
  or account-deletion defects block release.
- Open P0 core-flow defects block release.
- Lower-severity defects require documented impact, workaround, owner, and target
  release.

## 21. Rollout Plan

### Phase 0 — Internal development

- synthetic and team test data only;
- provider and cost instrumentation;
- security and privacy design review;
- architecture and design-system foundations;
- core session happy path.

### Phase 1 — Internal alpha

- approved employees and testers;
- limited language catalog;
- debug and operational telemetry validation;
- failure recovery and device matrix testing;
- no public production claims.

### Phase 2 — Closed beta

- invited external users;
- explicit beta disclosure;
- staged provider quotas;
- product feedback and quality measurement;
- validated support and deletion workflows.

### Phase 3 — Staged production

- percentage-based Google Play rollout;
- daily review of crashes, ANRs, session success, latency, cost, and support;
- documented stop and rollback thresholds;
- gradual expansion after health criteria pass.

### Phase 4 — General availability

- approved language and market catalog;
- production support coverage;
- finalized legal and privacy documentation;
- operational ownership and incident process;
- post-launch metric review cadence.

## 22. Launch Gates

Public production launch requires all of the following:

- all P0 requirements implemented or formally waived;
- core user journeys pass acceptance testing;
- crash, ANR, startup, and conversation latency meet targets;
- no known critical data-loss or unauthorized-recording defect;
- security threat model and review complete;
- privacy inventory, retention, deletion, and provider disclosures approved;
- Play Store data-safety and permission declarations verified;
- accessibility review complete;
- backend load and failure testing complete;
- dashboards and alerts operational;
- customer support and incident ownership assigned;
- signing, release, staged rollout, and rollback procedures tested;
- marketing claims match measured capability.

## 23. Success Criteria

Initial numerical product targets must be finalized after internal alpha produces
a reliable baseline. Before closed beta, Product must approve targets for:

- first-conversation completion rate;
- conversation start success rate;
- successful conversation rate;
- median and 95th-percentile time to first translation;
- seven-day retention;
- crash-free user and session rates;
- user-reported translation usefulness;
- support-contact rate per successful session;
- provider cost per successful session.

Targets must be segmented by app version, device tier, language pair, network
quality, and provider where sample size permits.

## 24. Product Decisions Required Before Launch

The following decisions are intentionally not assumed:

1. Which countries and interface locales launch first?
2. Which conversation languages and capabilities meet quality thresholds?
3. Is the application anonymous-first, account-required, or hybrid?
4. Is conversation history saved by default, requested per session, or disabled
   by default?
5. Is raw audio ever retained by Talkship or its providers?
6. What are the exact transcript and operational-data retention periods?
7. What free quota, paid plan, session limit, and purchase model apply?
8. Does active recording continue in the background?
9. Which backend, STT, translation, and TTS providers are approved?
10. What high-risk markets or use cases require additional restrictions?
11. What quality threshold qualifies a language pair for production?
12. Which metrics and consent controls are required in each launch market?

Each decision must be recorded in the PRD, an approved policy document, or an
Architecture Decision Record before the affected feature ships.

## 25. Delivery Milestones

### Milestone 1 — Foundation

- application shell;
- production design system;
- multi-module build foundation;
- navigation;
- analytics and logging contracts;
- backend API contracts;
- privacy and threat-model drafts.

### Milestone 2 — Core conversation

- language selection;
- permission flow;
- session lifecycle;
- speech recognition;
- translation;
- live transcript;
- text-to-speech playback;
- basic failure recovery.

### Milestone 3 — Persistence and control

- conversation history;
- session details;
- copy, replay, export, and delete;
- preferences;
- retention controls;
- process-death recovery.

### Milestone 4 — Production readiness

- account and entitlement if required;
- complete analytics;
- accessibility and localization;
- security and privacy closure;
- load, performance, migration, and device testing;
- support, monitoring, alerting, and incident procedures.

### Milestone 5 — Staged launch

- internal alpha;
- closed beta;
- production release candidate;
- staged rollout;
- launch health review.

## 26. Requirement Traceability

Every implementation task should reference at least one requirement ID.

Recommended traceability:

```text
PRD requirement
  -> product/design specification
  -> engineering task
  -> implementation module
  -> automated/manual test case
  -> analytics or operational signal
  -> release evidence
```

Requirement status should use:

- Proposed;
- Approved;
- In progress;
- Implemented;
- Verified;
- Deferred;
- Rejected.

Changes to P0 scope require Product, Engineering, and QA review. Changes that
affect user data, recording, consent, account deletion, billing, or security also
require the relevant Privacy, Legal, Security, or Finance owner.

## 27. Definition of Product Done

A Talkship feature is production-ready only when:

- its approved requirement and acceptance criteria are implemented;
- loading, empty, success, partial, offline, and failure states are handled;
- analytics and operational signals are validated without sensitive content;
- accessibility and localization requirements are met;
- privacy, security, and retention impacts are reviewed;
- tests pass at the appropriate levels;
- support and recovery paths exist;
- documentation is current;
- release and rollback behavior is understood;
- the feature is verified in a release-like staging environment.

Shipping code is not sufficient if the user cannot understand, trust, recover,
or control the feature.

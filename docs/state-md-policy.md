# state.md PR completion policy

## Rule
A pull request is not considered done until root-level `state.md` has been reviewed and, when project state changed, updated.

## Objective trigger criteria
`state.md` update is required when any changed file is outside docs/meta scope.

Docs/meta scope (exception candidates):
- `README.md`, `LICENSE`
- `docs/**`
- `.github/**`
- markdown/reStructuredText/plaintext/adoc-only edits

If any changed file falls outside this scope (for example app code, config, infra, tests, build/runtime files), update `state.md`.

## Allowed exceptions
- docs-only
- no-state-impact

Exceptions must be justified in PR description with explicit evidence.

## PR evidence format
Include these fields in PR description:
- Trigger decision: `required` or `not-required`
- Changed files reviewed
- Exception reason (if not-required)
- `state.md` update summary (if required)

## Enforcement
CI job `.github/workflows/state-md-policy.yml` evaluates changed files and PR body evidence.

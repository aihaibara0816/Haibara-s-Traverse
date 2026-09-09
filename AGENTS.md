# Haibara’s Traverse development guidance

Read README.md, docs/DESIGN.md, docs/VALIDATION.md and HANDOFF.md first.
Minecraft 1.21.1, NeoForge, Java 21; Create target 6.0.10. Keep core and api free of Minecraft/Create/AI imports.
Current mod_id remains haibaras_measurement for consistency; display/repository name may be Haibara’s Traverse. Do not migrate IDs casually.
Confirmed gameplay rules in docs/DESIGN.md are authoritative project requirements. Tools do not bind to projects. Public region ownership is server authoritative.
Never claim in-game success from static checks. Run bash gradlew build and coreCheck on JDK 21; report download/environment failures separately. Use gradlew.bat on Windows.
Do not implement fake successful AI or blueprint providers. Add optional adapters behind interfaces.
Next milestone: marker/stake blocks, ordered pen selection, save form, successful save consumes stakes, server world persistence and multiplayer ownership checks.
Use placeholder vanilla textures until user artwork arrives. Do not change scope to a website.

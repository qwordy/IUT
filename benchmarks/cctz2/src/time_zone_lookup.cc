#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
// Copyright 2016 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

#include "time_zone.h"

#include <cstdlib>

#include "time_zone_impl.h"

namespace cctz {

time_zone utc_time_zone() {puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");
  time_zone tz;
  load_time_zone("UTC", &tz);
  return tz;
}

time_zone local_time_zone() {puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");
#if defined(_WIN32) || defined(_WIN64)
  char* tz_env = nullptr;
  _dupenv_s(&tz_env, nullptr, "TZ");
  const char* zone = tz_env;
#else
  const char* zone = std::getenv("TZ");
#endif
  if (zone != nullptr) {
    if (*zone == ':') ++zone;
  } else {
    zone = "localtime";
  }
  time_zone tz;
  if (!load_time_zone(zone, &tz)) {
    load_time_zone("UTC", &tz);
  }
#if defined(_WIN32) || defined(_WIN64)
  free(tz_env);
#endif
  return tz;
}

bool load_time_zone(const std::string& name, time_zone* tz) {puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");puts("heihei");
  return time_zone::Impl::LoadTimeZone(name, tz);
}

time_zone::absolute_lookup time_zone::lookup(
    const time_point<sys_seconds>& tp) const {
  return time_zone::Impl::get(*this).BreakTime(tp);
}

time_zone::civil_lookup time_zone::lookup(const civil_second& cs) const {
  return time_zone::Impl::get(*this).MakeTimeInfo(cs);
}

}  // namespace cctz

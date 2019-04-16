import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysMemberLevel } from 'app/shared/model/sys-member-level.model';
import { SysMemberLevelService } from './sys-member-level.service';
import { SysMemberLevelComponent } from './sys-member-level.component';
import { SysMemberLevelDetailComponent } from './sys-member-level-detail.component';
import { SysMemberLevelUpdateComponent } from './sys-member-level-update.component';
import { SysMemberLevelDeletePopupComponent } from './sys-member-level-delete-dialog.component';
import { ISysMemberLevel } from 'app/shared/model/sys-member-level.model';

@Injectable({ providedIn: 'root' })
export class SysMemberLevelResolve implements Resolve<ISysMemberLevel> {
    constructor(private service: SysMemberLevelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysMemberLevel> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysMemberLevel>) => response.ok),
                map((sysMemberLevel: HttpResponse<SysMemberLevel>) => sysMemberLevel.body)
            );
        }
        return of(new SysMemberLevel());
    }
}

export const sysMemberLevelRoute: Routes = [
    {
        path: 'sys-member-level',
        component: SysMemberLevelComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-member-level/:id/view',
        component: SysMemberLevelDetailComponent,
        resolve: {
            sysMemberLevel: SysMemberLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-member-level/new',
        component: SysMemberLevelUpdateComponent,
        resolve: {
            sysMemberLevel: SysMemberLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-member-level/:id/edit',
        component: SysMemberLevelUpdateComponent,
        resolve: {
            sysMemberLevel: SysMemberLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysMemberLevelPopupRoute: Routes = [
    {
        path: 'sys-member-level/:id/delete',
        component: SysMemberLevelDeletePopupComponent,
        resolve: {
            sysMemberLevel: SysMemberLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberLevel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

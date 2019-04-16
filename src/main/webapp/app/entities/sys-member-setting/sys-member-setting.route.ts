import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysMemberSetting } from 'app/shared/model/sys-member-setting.model';
import { SysMemberSettingService } from './sys-member-setting.service';
import { SysMemberSettingComponent } from './sys-member-setting.component';
import { SysMemberSettingDetailComponent } from './sys-member-setting-detail.component';
import { SysMemberSettingUpdateComponent } from './sys-member-setting-update.component';
import { SysMemberSettingDeletePopupComponent } from './sys-member-setting-delete-dialog.component';
import { ISysMemberSetting } from 'app/shared/model/sys-member-setting.model';

@Injectable({ providedIn: 'root' })
export class SysMemberSettingResolve implements Resolve<ISysMemberSetting> {
    constructor(private service: SysMemberSettingService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysMemberSetting> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysMemberSetting>) => response.ok),
                map((sysMemberSetting: HttpResponse<SysMemberSetting>) => sysMemberSetting.body)
            );
        }
        return of(new SysMemberSetting());
    }
}

export const sysMemberSettingRoute: Routes = [
    {
        path: 'sys-member-setting',
        component: SysMemberSettingComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberSetting.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-member-setting/:id/view',
        component: SysMemberSettingDetailComponent,
        resolve: {
            sysMemberSetting: SysMemberSettingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberSetting.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-member-setting/new',
        component: SysMemberSettingUpdateComponent,
        resolve: {
            sysMemberSetting: SysMemberSettingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberSetting.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-member-setting/:id/edit',
        component: SysMemberSettingUpdateComponent,
        resolve: {
            sysMemberSetting: SysMemberSettingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberSetting.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysMemberSettingPopupRoute: Routes = [
    {
        path: 'sys-member-setting/:id/delete',
        component: SysMemberSettingDeletePopupComponent,
        resolve: {
            sysMemberSetting: SysMemberSettingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysMemberSetting.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysMemberSetting } from 'app/shared/model/sys-member-setting.model';
import { SysMemberSettingService } from './sys-member-setting.service';

@Component({
    selector: 'jhi-sys-member-setting-delete-dialog',
    templateUrl: './sys-member-setting-delete-dialog.component.html'
})
export class SysMemberSettingDeleteDialogComponent {
    sysMemberSetting: ISysMemberSetting;

    constructor(
        protected sysMemberSettingService: SysMemberSettingService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysMemberSettingService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysMemberSettingListModification',
                content: 'Deleted an sysMemberSetting'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-member-setting-delete-popup',
    template: ''
})
export class SysMemberSettingDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysMemberSetting }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysMemberSettingDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysMemberSetting = sysMemberSetting;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}

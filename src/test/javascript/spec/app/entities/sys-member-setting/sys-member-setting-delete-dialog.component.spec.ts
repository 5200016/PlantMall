/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysMemberSettingDeleteDialogComponent } from 'app/entities/sys-member-setting/sys-member-setting-delete-dialog.component';
import { SysMemberSettingService } from 'app/entities/sys-member-setting/sys-member-setting.service';

describe('Component Tests', () => {
    describe('SysMemberSetting Management Delete Component', () => {
        let comp: SysMemberSettingDeleteDialogComponent;
        let fixture: ComponentFixture<SysMemberSettingDeleteDialogComponent>;
        let service: SysMemberSettingService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMemberSettingDeleteDialogComponent]
            })
                .overrideTemplate(SysMemberSettingDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysMemberSettingDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMemberSettingService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysClassifyDeleteDialogComponent } from 'app/entities/sys-classify/sys-classify-delete-dialog.component';
import { SysClassifyService } from 'app/entities/sys-classify/sys-classify.service';

describe('Component Tests', () => {
    describe('SysClassify Management Delete Component', () => {
        let comp: SysClassifyDeleteDialogComponent;
        let fixture: ComponentFixture<SysClassifyDeleteDialogComponent>;
        let service: SysClassifyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysClassifyDeleteDialogComponent]
            })
                .overrideTemplate(SysClassifyDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysClassifyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysClassifyService);
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
